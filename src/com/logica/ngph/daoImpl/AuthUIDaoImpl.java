package com.logica.ngph.daoImpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.common.PaymentStatusEnum;
import com.logica.ngph.dao.AuthUIDao;


public class AuthUIDaoImpl extends HibernateDaoSupport implements AuthUIDao {
	
	SessionFactory fact =null;
	Session sess =null;
	Query query =null;

	/**
	 * This method is called when the User will Reject the Transaction
	 * The table will be updated based on business needs
	 */
	public String updateTableforReject(String supId, String authRef, String reject)
	{
		String response = null;

		try
		{
			fact=getHibernateTemplate().getSessionFactory();
			sess=fact.openSession();
			
		// For Storing GroupId and their Sequence.
		Map<String, String> groupInfo = new TreeMap<String, String>();
		
		//Stores info about man or optional for a particular group
		Set<String> manChecker  = new TreeSet<String>();
		
		// Fetching the Highest AuthCycle from TA_AUTHSTATUST table
		List val = getHibernateTemplate().find("select max(authCycle) as AuthCycle from com.logica.ngph.Entity.AuthUIStatusT where authRef=?",authRef);
		
		System.out.println("1111111111111111");
		int maxAuthCyle = new Integer(val.get(0).toString()).intValue();
		System.out.println("Highest Auth Cycle in Db is : " + maxAuthCyle);
		
		// Checking for Auth Time and Reject Time is null or not
		List timeData = getHibernateTemplate().find("select authTime, rejectedTime from com.logica.ngph.Entity.AuthUIStatusT where authRef=? and authCycle=?",authRef,maxAuthCyle+"");
		
		int timeDataSize= timeData.size();
		
		for (int i = 0; i < timeDataSize ; i++) {
            Object[] obj = (Object[]) timeData.get(i);
            
            System.out.println("authTime : "+ obj[0]);
            System.out.println("Rejected Time : "+ obj[1]);

            // Handling Concurrency
            if(obj[0]==null && obj[1]==null )
    		{
    			System.out.println("Auth Time and Rejected time is Null");
    			
    			// Fetching Group Id and Group seq for a particular Auth Ref
    			List grpInf = getHibernateTemplate().find("select groupID, groupSeq from com.logica.ngph.Entity.AuthUIStatusD where authRef=? order by groupseq",authRef);
    			
    			if(grpInf!=null)
    			{
    				int size = grpInf.size();
    				for(int k=0;k<size;k++)
    				{
    					Object[] grp = (Object[]) grpInf.get(k);
    					groupInfo.put(grp[1].toString(), grp[0].toString());
    				}
    			}
    				System.out.println(groupInfo);
    				
    				// This List contains all the sup_mandatory check, whether they are mandatory or not as data 0,0,0 or 0,1,2
    				List checkManOpt = getHibernateTemplate().find("select supMan from com.logica.ngph.Entity.AuthUIStatusD where groupID=(select groupID from com.logica.ngph.Entity.AuthUIStatusD where supId='" + supId +"') order by supSeq");
    				
    				if(checkManOpt!=null)
    				{
    					int size=checkManOpt.size();
    					for(int l=0;l<size;l++)
    					{
    						//System.out.println(checkManOpt.get(l));
    						
    						// Storing mandatory options in a local Tree Set
    						manChecker.add(checkManOpt.get(l).toString());
    					}
    				}
    				
    				System.out.println(manChecker);
    				
    				// Data fetching and acting conditionally for particular sup Id.
    				List data = getHibernateTemplate().find("select groupSeq,supMan from com.logica.ngph.Entity.AuthUIStatusD where supId='" + supId +"'");

    				if(data!=null)
    				{
    					int size=data.size();
    					for(int c=0;c<size;c++)
    					{
    						Object[] vals = (Object[])data.get(c);
    						System.out.println("GroupSeq" + "\t" + "SupMan");
    						System.out.println(vals[0] + "\t\t" + vals[1]);
    						
    						int grpSeq =(new Integer( vals[0].toString())).intValue();
    						String isMan = vals[1].toString();
    						
    						// Supervisor is Optional
    						if(isMan.matches("0"))
    						{
    							System.out.println("Supervisor is Optional");
    							
 	    							// Updating optional sup auth status and its auth time
	    							String hql = "update com.logica.ngph.Entity.AuthUIStatusD set authStatus = :aStatus, authTime = :aTime where supId= :aSupId and groupSeq= :aGroupSeq and authRef= :aAuthRef and authStatus= :OldAuthStatus";
	    							query = sess.createQuery(hql);
	    							
	    							query.setString("aStatus","R");
	    							query.setDate("aTime",new Date());
	    							query.setString("aSupId",supId);
	    							query.setInteger("aGroupSeq", grpSeq);
	    							query.setString("aAuthRef", authRef);
	    							query.setString("OldAuthStatus","P");
	    							
	    							int result=query.executeUpdate();
	    							System.out.println("No of records updated are : " + result);
     							
	    							//Handling Concurrency
	    							if(result>0)
	    							{
	    								System.out.println("Values Populated for the user, First user has updated the value");
	    								
	    								response = "sucess";
	    							}
	    							else
	    							{	// @TODO Response needs to be send to the user..integration
	    								System.out.println("Values already updated...needs to refresh your page");
	    								response = "refresh";
	    								break;
	    								
	    							}
    							// Check whether all supervisors in a group are optional or not
    							boolean checkisMan = false;
    							Iterator itr = manChecker.iterator();
    							while(itr.hasNext())
    							{
    								String value = itr.next().toString();
    								if(value.matches("0"))
    								{
    									//System.out.println(value);
    									checkisMan=true;
    								}
    								else
    								{
    									checkisMan=false;
    								}
    							}
    							// There is a mandatory supervisor also in a group
    							if(checkisMan==false)
    							{
    								System.out.println("There are other mandatory supervisors also in the group, hence exit and take no actions");
    							}
    							//All the supervisor are optional in a group
    							else
    							{
    								System.out.println("There are all optional supervisors");
    								
 	    								// Update optional supervisor status as 'D' where status was 'P'
	    								String updateOptional = "update AuthUIStatusD set authStatus = :aStatus, authTime = :aTime where groupSeq= :aGropuSeq and authStatus= :aAuthStatus and authRef= :aAuthRef";
	        							query = sess.createQuery(updateOptional);
	        							
	        							query.setString("aStatus","D");
	        							query.setDate("aTime",new Date());
	        							query.setInteger("aGropuSeq", grpSeq);
	        							query.setString("aAuthStatus","P");
	        							query.setString("aAuthRef", authRef);
	        							int updateResult = query.executeUpdate();
	        							System.out.println("No of optional Records updated are : " + updateResult);
	        							
	        							// Updating the Group status to 'R' for all the optional supervisor group
	        							String updateGroupStatus = "update AuthUIStatusD set groupAuthStatus = :agroupAuthStatus where groupSeq= :aGropuSeq and authRef= :aAuthRef";
	        							query = sess.createQuery(updateGroupStatus);
	        							
	        							query.setString("agroupAuthStatus","R");
	        							query.setInteger("aGropuSeq", grpSeq);
	        							query.setString("aAuthRef", authRef);
	        							
	        							int updateGroupStatusResult = query.executeUpdate();
	        							System.out.println("No of Group Status optional Records updated are : " + updateGroupStatusResult);

     							// Check if Other Group Exists or not
    							int grpSeqNextVal = (int)grpSeq + 1;
    							if(groupInfo.get(grpSeqNextVal+"")!=null)
    							{
    								System.out.println("Next Group Sequence is present");
    								System.out.println("Next group Sequence is : " + grpSeqNextVal);
    								
 	    								// Updating the next Group status to P from null
	    								String updateNextGroupStatus = "update AuthUIStatusD set groupAuthStatus = :agroupAuthStatus where groupSeq= :aGropuSeq and authRef= :aAuthRef";
	        							query = sess.createQuery(updateNextGroupStatus);
	        							
	        							query.setString("agroupAuthStatus","P");
	        							query.setInteger("aGropuSeq", grpSeqNextVal);
	        							query.setString("aAuthRef", authRef);
	        							
	        							int updateNextGroupStatusResult = query.executeUpdate();
	        							System.out.println("No of Group Status optional Records updated are : " + updateNextGroupStatusResult);
	        							
	        							// Updating next Auth Status for optional supervisors to P from null and their latest recieved time
	        							String updateNextAuthStatus = "update AuthUIStatusD set authStatus = :aAuthStatus, recTime = :aRecTime where groupSeq= :aGropuSeq and authRef= :aAuthRef and supMan= :aSupMan";
	        							query = sess.createQuery(updateNextAuthStatus);
	        							
	        							query.setString("aAuthStatus","P");
	        							query.setDate("aRecTime",new Date());
	        							query.setInteger("aGropuSeq", grpSeqNextVal);
	        							query.setString("aAuthRef", authRef);
	        							query.setInteger("aSupMan", 0);
	        							
	        							int updateNextGroupAuthStatusResult = query.executeUpdate();
	        							System.out.println("No of Records updated for Optional Supervisor are : " + updateNextGroupAuthStatusResult);

	        							// Fetching the first Mandatory Supervisor in next group
	        							List getFirstManSupSeq = getHibernateTemplate().find("select min(supSeq) from com.logica.ngph.Entity.AuthUIStatusD  where authRef='"+authRef+"' and groupSeq="+grpSeqNextVal+" and supSeq<>0");
    									int firstManSupSeq = new Integer(getFirstManSupSeq.get(0).toString()).intValue();
    									System.out.println("Fisrt Mandatory supervisor in the next Group is : " + firstManSupSeq);
    									
	        							//Updating first mandatory supervisor to P from null and his latest recieved time
	        							String updateNextAuthStatusForMand = "update AuthUIStatusD set authStatus = :aAuthStatus, recTime = :aRecTime where groupSeq= :aGropuSeq and authRef= :aAuthRef and supSeq= :aSupSeq";
	        							query = sess.createQuery(updateNextAuthStatusForMand);
	        							
	        							query.setString("aAuthStatus","P");
	        							query.setDate("aRecTime",new Date());
	        							query.setInteger("aGropuSeq", grpSeqNextVal);
	        							query.setString("aAuthRef", authRef);
	        							query.setInteger("aSupSeq", firstManSupSeq);
	        							
	        							int updateNextGroupAuthStatusResultForMand = query.executeUpdate();
	        							System.out.println("No of Records updated for Fisrt Mandatory Supervisor are : " + updateNextGroupAuthStatusResultForMand);

     							}
    							// If the other group does not exists
    							else
    							{
    								System.out.println("Next Group does not exists");
    								
    								// Updating TA_AUTHSTATUST REJECTEDTIME 
    								String updateRejectTimeforT = "update AuthUIStatusT set rejectedTime = :aRejectedTime where authRef= :aAuthRef";
        							query = sess.createQuery(updateRejectTimeforT);
        							
        							query.setDate("aRejectedTime",new Date());
        							query.setString("aAuthRef", authRef);
        							
        							int updateRejectTimeforTResult = query.executeUpdate();
        							System.out.println("No of Group Status optional Records updated are : " + updateRejectTimeforTResult);
        							
        							// updating tx_Messages
 
        							String msgRef = authRef.substring(0, authRef.length()-6);
        							System.out.println("The Message Refrence is : " + msgRef);
        							
        							String msgStatus=null;
        							String prevMsgStatus=null;
        							String direction=null;
        							String newStatus = null;

        							// Fetch prev Msg status and current msgStatus and Direction based on msgRef.
        							
        							List txMsgData = getHibernateTemplate().find("select msgStatus, msgPrevStatus, msgDirection from com.logica.ngph.Entity.NgphCanonical where msgRef='"+msgRef+"'");
									if(txMsgData!=null)
									{
										int DataSize= txMsgData.size();
										
										for (int n = 0; n < DataSize ; n++) {
								            Object[] vData = (Object[]) txMsgData.get(n);
								            
								            msgStatus = vData[0].toString();
								            prevMsgStatus = vData[1].toString();
								            direction = vData[2].toString();
								            
								            System.out.println("Current Message Status : "+ msgStatus);
								            System.out.println("Previous Message Status : "+ prevMsgStatus);
								            System.out.println("Direction of the Payment : "+ direction);

										}
									}
									// Checking the direction of the payment
									if(direction.equalsIgnoreCase("I"))
									{
										newStatus = PaymentStatusEnum.findPaymentStatusEnumByName("AWAITING_REPAIR_I");
									}
									else
									{
										newStatus = PaymentStatusEnum.findPaymentStatusEnumByName("AWAITING_REPAIR");
									}
									
        							// put msgsts val to prevmsgsts and update mgsts with the new value Awaiting Repair
        							String updateMsgStatusForTxMsg = "update com.logica.ngph.Entity.NgphCanonical set msgStatus = :aMsgStatus and msgPrevStatus = :aMsgPrevStatus and lastModTime= :aLastModTime where msgRef= :aMsgRef";
        							query = sess.createQuery(updateMsgStatusForTxMsg);
        							
        							query.setString("aMsgStatus", newStatus);
        							query.setString("aMsgPrevStatus", msgStatus);
        							query.setDate("aLastModTime", new Date());
        							query.setString("aMsgRef", msgRef);
        							
        							int updateTxMsg= query.executeUpdate();
        							System.out.println("No of Records updated are : " + updateTxMsg);
        							
        							// Raise Event waiting for Modular approach
        							//TODO
         						}
    						}
    					}
    						// Supervisor is Mandatory
    						else
    						{
    							System.out.println("Supervisor is mandatory");
    							
 	    							// Updating Mandatory sup auth status and its auth time
	    							String hql = "update com.logica.ngph.Entity.AuthUIStatusD set authStatus = :aStatus, authTime = :aTime where supId= :aSupId and groupSeq= :aGroupSeq and authRef= :aAuthRef and authStatus=:OldAuthStatus";
	    							query = sess.createQuery(hql);
	    							
	    							query.setString("aStatus","R");
	    							query.setDate("aTime",new Date());
	    							query.setString("aSupId",supId);
	    							query.setInteger("aGroupSeq", grpSeq);
	    							query.setString("aAuthRef", authRef);
	    							query.setString("OldAuthStatus","P");
	    							int result=query.executeUpdate();
	    							
	    							System.out.println("No of records updated are : " + result);
	    							System.out.println(hql+"77");
	    							//Handling Concurrency
	    							if(result>0)
	    							{
	    								System.out.println("Values Populated for the user, First user has updated the value");
	    								
	    								response = "sucess";
	    							}
	    							else
	    							{	
	    								System.out.println("Values already updated...needs to refresh your page");
	    								response = "refresh";
	    								break;
	    								
	    							}
     							
    							// Check whether all supervisors in a group are optional or not
    							boolean checkisMan = false;
    							Iterator itr = manChecker.iterator();
    							while(itr.hasNext())
    							{
    								String value = itr.next().toString();
    								if(value.matches("0"))
    								{
    									//System.out.println(value);
    									checkisMan=true;
    								}
    								else
    								{
    									checkisMan=false;
    								}
    							}
    							// There are other mandatory supervisor also in a group
    							if(checkisMan==false)
    							{
    								System.out.println("There are other mandatory supervisors also in the group for the Mandatory Supervisors");
    								
 	    								// Update other Mandatory supervisors as 'D' where status was Null
	    								String updateOtherMandSup = "update AuthUIStatusD set authStatus = :aStatus, authTime = :aTime where groupSeq= :aGropuSeq and authStatus= :aAuthStatus and authRef= :aAuthRef";
	        							query = sess.createQuery(updateOtherMandSup);
	        							
	        							query.setString("aStatus","D");
	        							query.setDate("aTime",new Date());
	        							query.setInteger("aGropuSeq", grpSeq);
	        							query.setString("aAuthStatus","null");
	        							query.setString("aAuthRef", authRef);
	        							int updateOtherMandSupResult = query.executeUpdate();
	        							System.out.println("No of optional Records updated are : " + updateOtherMandSupResult);
	        							
	        							// Update Optional Supervisors as 'D' where status was 'P'
	        							String updateOtherOptSup = "update AuthUIStatusD set authStatus = :aStatus, authTime = :aTime where groupSeq= :aGropuSeq and authStatus= :aAuthStatus and authRef= :aAuthRef";
	        							query = sess.createQuery(updateOtherOptSup);
	        							
	        							query.setString("aStatus","D");
	        							query.setDate("aTime",new Date());
	        							query.setInteger("aGropuSeq", grpSeq);
	        							query.setString("aAuthStatus","P");
	        							query.setString("aAuthRef", authRef);
	        							int updateOtherOptSupResult = query.executeUpdate();
	        							System.out.println("No of optional Records updated are : " + updateOtherOptSupResult);
	        							
	        							// Updating the Group Status to 'R' for all the supervisors in a group
	        							String updateGroupStatus = "update AuthUIStatusD set groupAuthStatus = :agroupAuthStatus where groupSeq= :aGropuSeq and authRef= :aAuthRef";
	        							query = sess.createQuery(updateGroupStatus);
	        							
	        							query.setString("agroupAuthStatus","R");
	        							query.setInteger("aGropuSeq", grpSeq);
	        							query.setString("aAuthRef", authRef);
	        							
	        							int updateGroupStatusResult = query.executeUpdate();
	        							System.out.println("No of Group Status optional Records updated are : " + updateGroupStatusResult);
     								
        							// Check if Other Group Exists or not
        							int grpSeqNextVal = (int)grpSeq + 1;
        							if(groupInfo.get(grpSeqNextVal+"")!=null)
        							{
        								System.out.println("Next Group Sequence is present");
        								System.out.println("Next group Sequence is : " + grpSeqNextVal);
        								
    	    								// Updating the next Group status to P from null
    	    								String updateNextGroupStatus = "update AuthUIStatusD set groupAuthStatus = :agroupAuthStatus where groupSeq= :aGropuSeq and authRef= :aAuthRef";
    	        							query = sess.createQuery(updateNextGroupStatus);
    	        							
    	        							query.setString("agroupAuthStatus","P");
    	        							query.setInteger("aGropuSeq", grpSeqNextVal);
    	        							query.setString("aAuthRef", authRef);
    	        							
    	        							int updateNextGroupStatusResult = query.executeUpdate();
    	        							System.out.println("No of Group Status optional Records updated are : " + updateNextGroupStatusResult);
    	        							
    	        							// Updating next Auth Status for optional supervisors to P from null and their latest recieved time
    	        							String updateNextAuthStatus = "update AuthUIStatusD set authStatus = :aAuthStatus, recTime = :aRecTime where groupSeq= :aGropuSeq and authRef= :aAuthRef and supMan= :aSupMan";
    	        							query = sess.createQuery(updateNextAuthStatus);
    	        							
    	        							query.setString("aAuthStatus","P");
    	        							query.setDate("aRecTime",new Date());
    	        							query.setInteger("aGropuSeq", grpSeqNextVal);
    	        							query.setString("aAuthRef", authRef);
    	        							query.setInteger("aSupMan", 0);
    	        							
    	        							int updateNextGroupAuthStatusResult = query.executeUpdate();
    	        							System.out.println("No of Records updated for Optional Supervisor are : " + updateNextGroupAuthStatusResult);

    	        							// Fetching the first Mandatory Supervisor in next group
    	        							List getFirstManSupSeq = getHibernateTemplate().find("select min(supSeq) from com.logica.ngph.Entity.AuthUIStatusD  where authRef='"+authRef+"' and groupSeq="+grpSeqNextVal+" and supSeq<>0");
        									int firstManSupSeq = new Integer(getFirstManSupSeq.get(0).toString()).intValue();
        									System.out.println("Fisrt Mandatory supervisor in the next Group is : " + firstManSupSeq);
        									
    	        							//Updating first mandatory supervisor to P from null and their latest recieved time
    	        							String updateNextAuthStatusForMand = "update AuthUIStatusD set authStatus = :aAuthStatus, recTime = :aRecTime where groupSeq= :aGropuSeq and authRef= :aAuthRef and supSeq= :aSupSeq";
    	        							query = sess.createQuery(updateNextAuthStatusForMand);
    	        							
    	        							query.setString("aAuthStatus","P");
    	        							query.setDate("aRecTime",new Date());
    	        							query.setInteger("aGropuSeq", grpSeqNextVal);
    	        							query.setString("aAuthRef", authRef);
    	        							query.setInteger("aSupSeq", firstManSupSeq);
    	        							
    	        							int updateNextGroupAuthStatusResultForMand = query.executeUpdate();
    	        							System.out.println("No of Records updated for Fisrt Mandatory Supervisor are : " + updateNextGroupAuthStatusResultForMand);

        							}
        							// If the other group does not exists
        							else
        							{
        								System.out.println("Next Group does not exists");
        								
        								// Updating TA_AUTHSTATUST REJECTEDTIME 
        								String updateRejectTimeforT = "update AuthUIStatusT set rejectedTime = :aRejectedTime where authRef= :aAuthRef";
            							query = sess.createQuery(updateRejectTimeforT);
            							
            							query.setDate("aRejectedTime",new Date());
            							query.setString("aAuthRef", authRef);
            							
            							int updateRejectTimeforTResult = query.executeUpdate();
            							System.out.println("No of Group Status optional Records updated are : " + updateRejectTimeforTResult);
            							
            							// updating tx_Messages
            							 
            							String msgRef = authRef.substring(0, authRef.length()-6);
            							System.out.println("The Message Refrence is : " + msgRef);
            							
            							String msgStatus=null;
            							String prevMsgStatus=null;
            							String direction=null;
            							String newStatus = null;

            							// Fetch prev Msg status and current msgStatus and Direction based on msgRef.
            							
            							List txMsgData = getHibernateTemplate().find("select msgStatus, msgPrevStatus, msgDirection from com.logica.ngph.Entity.NgphCanonical where msgRef='"+msgRef+"'");
    									if(txMsgData!=null)
    									{
    										int DataSize= txMsgData.size();
    										
    										for (int n = 0; n < DataSize ; n++) {
    								            Object[] vData = (Object[]) txMsgData.get(n);
    								            
    								            msgStatus = vData[0].toString();
    								            prevMsgStatus = vData[1].toString();
    								            direction = vData[2].toString();
    								            
    								            System.out.println("Current Message Status : "+ msgStatus);
    								            System.out.println("Previous Message Status : "+ prevMsgStatus);
    								            System.out.println("Direction of the Payment : "+ direction);

    										}
    									}
    									// Checking the direction of the payment
    									if(direction.equalsIgnoreCase("I"))
    									{
    										newStatus = PaymentStatusEnum.findPaymentStatusEnumByName("AWAITING_REPAIR_I");
    									}
    									else
    									{
    										newStatus = PaymentStatusEnum.findPaymentStatusEnumByName("AWAITING_REPAIR");
    									}
    									
            							// put msgsts val to prevmsgsts and update mgsts with the new value Awaiting Repair
            							String updateMsgStatusForTxMsg = "update com.logica.ngph.Entity.NgphCanonical set msgStatus = :aMsgStatus and msgPrevStatus = :aMsgPrevStatus and lastModTime= :aLastModTime where msgRef= :aMsgRef";
            							query = sess.createQuery(updateMsgStatusForTxMsg);
            							
            							query.setString("aMsgStatus", newStatus);
            							query.setString("aMsgPrevStatus", msgStatus);
            							query.setDate("aLastModTime", new Date());
            							query.setString("aMsgRef", msgRef);
            							
            							int updateTxMsg= query.executeUpdate();
            							System.out.println("No of Records updated are : " + updateTxMsg);
            							
            							// Raise Event waiting for Modular approach
            							//TODO
             							
        							}
    							}
    							
    							//All the other supervisor are optional in a group
    							else
    							{
    								System.out.println("All other supervisors are optional in the group");
    								// Update Optional Supervisors as 'D' where status was 'P'
        							String updateOtherOptSup = "update AuthUIStatusD set authStatus = :aStatus, authTime = :aTime where groupSeq= :aGropuSeq and authStatus= :aAuthStatus and authRef= :aAuthRef";
        							query = sess.createQuery(updateOtherOptSup);
        							
        							query.setString("aStatus","D");
        							query.setDate("aTime",new Date());
        							query.setInteger("aGropuSeq", grpSeq);
        							query.setString("aAuthStatus","P");
        							query.setString("aAuthRef", authRef);
        							int updateOtherOptSupResult = query.executeUpdate();
        							System.out.println("No of optional Records updated are : " + updateOtherOptSupResult);
        							
        							// Updating the Group Status to 'R' for all the supervisors in a group
        							String updateGroupStatus = "update AuthUIStatusD set groupAuthStatus = :agroupAuthStatus where groupSeq= :aGropuSeq and authRef= :aAuthRef";
        							query = sess.createQuery(updateGroupStatus);
        							
        							query.setString("agroupAuthStatus","R");
        							query.setInteger("aGropuSeq", grpSeq);
        							query.setString("aAuthRef", authRef);
        							
        							int updateGroupStatusResult = query.executeUpdate();
        							System.out.println("No of Group Status optional Records updated are : " + updateGroupStatusResult);
        							
        							// Updating TA_AUTHSTATUST REJECTEDTIME 
    								String updateRejectTimeforT = "update AuthUIStatusT set rejectedTime = :aRejectedTime where authRef= :aAuthRef";
        							query = sess.createQuery(updateRejectTimeforT);
        							
        							query.setDate("aRejectedTime",new Date());
        							query.setString("aAuthRef", authRef);
        							
        							int updateRejectTimeforTResult = query.executeUpdate();
        							System.out.println("No of Group Status optional Records updated are : " + updateRejectTimeforTResult);
        							
        							// updating tx_Messages
          							 
        							String msgRef = authRef.substring(0, authRef.length()-6);
        							System.out.println("The Message Refrence is : " + msgRef);
        							
        							String msgStatus=null;
        							String prevMsgStatus=null;
        							String direction=null;
        							String newStatus = null;

        							// Fetch prev Msg status and current msgStatus and Direction based on msgRef.
        							
        							List txMsgData = getHibernateTemplate().find("select msgStatus, msgPrevStatus, msgDirection from com.logica.ngph.Entity.NgphCanonical where msgRef='"+msgRef+"'");
									if(txMsgData!=null)
									{
										int DataSize= txMsgData.size();
										
										for (int n = 0; n < DataSize ; n++) {
								            Object[] vData = (Object[]) txMsgData.get(n);
								            
								            msgStatus = vData[0].toString();
								            prevMsgStatus = vData[1].toString();
								            direction = vData[2].toString();
								            
								            System.out.println("Current Message Status : "+ msgStatus);
								            System.out.println("Previous Message Status : "+ prevMsgStatus);
								            System.out.println("Direction of the Payment : "+ direction);

										}
									}
									// Checking the direction of the payment
									if(direction.equalsIgnoreCase("I"))
									{
										newStatus = PaymentStatusEnum.findPaymentStatusEnumByName("AWAITING_REPAIR_I");
										
									}
									else
									{
										newStatus = PaymentStatusEnum.findPaymentStatusEnumByName("AWAITING_REPAIR");
										
									}
									
        							// put msgsts val to prevmsgsts and update mgsts with the new value Awaiting Repair
        							String updateMsgStatusForTxMsg = "update com.logica.ngph.Entity.NgphCanonical set msgStatus = :aMsgStatus and msgPrevStatus = :aMsgPrevStatus and lastModTime= :aLastModTime where msgRef= :aMsgRef";
        							query = sess.createQuery(updateMsgStatusForTxMsg);
        							
        							query.setString("aMsgStatus", newStatus);
        							query.setString("aMsgPrevStatus", msgStatus);
        							query.setDate("aLastModTime", new Date());
        							query.setString("aMsgRef", msgRef);
        							
        							int updateTxMsg= query.executeUpdate();
        							System.out.println("No of Records updated are : " + updateTxMsg);
        							
        							// Raise Event waiting for Modular approach and Pass the message to Repair Queue.
        							//TODO
         							
     								
    							}
    							
    						}
    					}
    				}
    			
    			}
        	// If Auth Time or Rejected time is not null
    		else
    		{	
    			//@ToDO Show Dialog Box, needs to integrate with Kesavan code
    			System.out.println("Auth Time or Reject Time is not Null");
    			System.out.println("The State has been changed hence page needs to be refreshed");
    			response="refresh";
    		}
		}
	}// try
	catch (Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		if(sess!=null && fact!=null)
		{
			sess.flush();
			sess.close();
			fact.close();
		}
	}
	return response;
	}
	
	/**
	 * This method is called when the User will Authorize the Transaction
	 * The table will be updated based on business needs
	 */
	public String updateTableforAuthorize(String supId, String authRef, String authorize)
	{
		String response = null;
		
		try
		{
			fact=getHibernateTemplate().getSessionFactory();
			sess=fact.openSession();

		// For Storing GroupId and their Sequence.
		Map<String, String> groupInfo = new TreeMap<String, String>();
		
		//Stores info about man or optional for a particular group
		Set<String> manChecker  = new TreeSet<String>();
		
		// Fetching the Highest AuthCycle from TA_AUTHSTATUST table
		List val = getHibernateTemplate().find("select max(authCycle) as AuthCycle from com.logica.ngph.Entity.AuthUIStatusT where authRef=?",authRef);
		
		int maxAuthCyle = new Integer(val.get(0).toString()).intValue();
		System.out.println("Highest Auth Cycle in Db is : " + maxAuthCyle);
		
		// Checking for Auth Time and Reject Time is null or not
		List timeData = getHibernateTemplate().find("select authTime, rejectedTime from com.logica.ngph.Entity.AuthUIStatusT where authRef=? and authCycle=?",authRef,maxAuthCyle+"");
		
		int timeDataSize= timeData.size();
		
		for (int i = 0; i < timeDataSize ; i++) {
            Object[] obj = (Object[]) timeData.get(i);
            
            System.out.println("Auth Time : "+obj[0]);
            System.out.println("Rejected Time : "+obj[1]);
            
            if(obj[0]==null && obj[1]==null )
    		{
    			System.out.println("Auth Time and Rejected time is Null");
    			
    			List grpInf = getHibernateTemplate().find("select groupID, groupSeq from com.logica.ngph.Entity.AuthUIStatusD where authRef=? order by groupseq",authRef);
    			
    			if(grpInf!=null)
    			{
    				int size = grpInf.size();
    				for(int k=0;k<size;k++)
    				{
    					Object[] grp = (Object[]) grpInf.get(k);
    					groupInfo.put(grp[1].toString(), grp[0].toString());
    				}
    			}
    				System.out.println(groupInfo);
    				
    				// This List contains all the sup_mandatory check, whether they are mandatory or not as data 0,0,0 or 0,1,2
    				List checkManOpt = getHibernateTemplate().find("select supMan from com.logica.ngph.Entity.AuthUIStatusD where groupID=(select groupID from com.logica.ngph.Entity.AuthUIStatusD where supId='" + supId +"') order by supSeq");
    				
    				if(checkManOpt!=null)
    				{
    					int size=checkManOpt.size();
    					for(int l=0;l<size;l++)
    					{
    						//System.out.println(checkManOpt.get(l));
    						
    						// Storing mandatory options in a local Tree Set
    						manChecker.add(checkManOpt.get(l).toString());
    					}
    				}
    				
    				System.out.println(manChecker);
    				
    				// Data fetching and acting conditionally for particular sup Id.
    				List data = getHibernateTemplate().find("select groupSeq,supMan,supSeq from com.logica.ngph.Entity.AuthUIStatusD where supId='" + supId +"'");

    				if(data!=null)
    				{
    					int size=data.size();
    					for(int c=0;c<size;c++)
    					{
    						Object[] vals = (Object[])data.get(c);
    						System.out.println("GroupSeq" + "\t" + "SupMan");
    						System.out.println(vals[0] + "\t\t" + vals[1]);
    						
    						int grpSeq =(new Integer( vals[0].toString())).intValue();
    						String isMan = vals[1].toString();
    						int supSeq = (new Integer( vals[2].toString())).intValue(); 
    						
    						// Supervisor is Optional
    						if(isMan.matches("0"))
    						{
    							System.out.println("Supervisor is Optional");
    								
	    							// Updating optional sup auth status and its auth time
	    							String hql = "update com.logica.ngph.Entity.AuthUIStatusD set authStatus = :aStatus, authTime = :aTime where supId= :aSupId and groupSeq= :aGroupSeq and authRef= :aAuthRef and authStatus =:OldStatus";
	    							query = sess.createQuery(hql);
	    							
	    							query.setString("aStatus","A");
	    							query.setDate("aTime",new Date());
	    							query.setString("aSupId",supId);
	    							query.setInteger("aGroupSeq", grpSeq);
	    							query.setString("aAuthRef", authRef);
	    							query.setString("OldStatus","P");
	    							
	    							int result=query.executeUpdate();
	    							
	    							System.out.println("No of records updated are : " + result);
	    							//Handling Concurrency
	    							if(result>0)
	    							{
	    								System.out.println("Values Populated for the user, First user has updated the value");
	    								
	    								response = "sucess";
	    							}
	    							else
	    							{	// @TODO Response needs to be send to the user..integration
	    								System.out.println("Values already updated...needs to refresh your page");
	    								response = "refresh";
	    								break;
	    							}
    							
    							// Check whether all supervisors in a group are optional or not
    							boolean checkisMan = false;
    							Iterator itr = manChecker.iterator();
    							while(itr.hasNext())
    							{
    								String value = itr.next().toString();
    								if(value.matches("0"))
    								{
    									//System.out.println(value);
    									checkisMan=true;
    								}
    								else
    								{
    									checkisMan=false;
    								}
    							}
    							// There is a mandatory supervisor also in a group
    							if(checkisMan==false)
    							{
    								System.out.println("There are other mandatory supervisors also in the group, hence exit and take no actions");
    							}
    							//All the supervisor are optional in a group
    							else
    							{
    								System.out.println("There are all optional supervisors");
    								
 	    								// Update optional supervisor status as 'D' where status was 'P'
	    								String updateOptional = "update AuthUIStatusD set authStatus = :aStatus, authTime = :aTime where groupSeq= :aGropuSeq and authStatus= :aAuthStatus and authRef= :aAuthRef";
	        							query = sess.createQuery(updateOptional);
	        							
	        							query.setString("aStatus","D");
	        							query.setDate("aTime",new Date());
	        							query.setInteger("aGropuSeq", grpSeq);
	        							query.setString("aAuthStatus","P");
	        							query.setString("aAuthRef", authRef);
	        							int updateResult = query.executeUpdate();
	        							System.out.println("No of optional Records updated are : " + updateResult);
	        							
	        							// Updating the Group status to 'R' for all the optional supervisor group
	        							String updateGroupStatus = "update AuthUIStatusD set groupAuthStatus = :agroupAuthStatus where groupSeq= :aGropuSeq and authRef= :aAuthRef";
	        							query = sess.createQuery(updateGroupStatus);
	        							
	        							query.setString("agroupAuthStatus","A");
	        							query.setInteger("aGropuSeq", grpSeq);
	        							query.setString("aAuthRef", authRef);
	        							
	        							int updateGroupStatusResult = query.executeUpdate();
	        							System.out.println("No of Group Status optional Records updated are : " + updateGroupStatusResult);

     							// Check if Other Group Exists or not
    							int grpSeqNextVal = (int)grpSeq + 1;
    							if(groupInfo.get(grpSeqNextVal+"")!=null)
    							{
    								System.out.println("Next Group Sequence is present");
    								System.out.println("Next group Sequence is : " + grpSeqNextVal);
    								
 	    								
	    								// Updating the next Group status to P from null
	    								String updateNextGroupStatus = "update AuthUIStatusD set groupAuthStatus = :agroupAuthStatus where groupSeq= :aGropuSeq and authRef= :aAuthRef";
	        							query = sess.createQuery(updateNextGroupStatus);
	        							
	        							query.setString("agroupAuthStatus","P");
	        							query.setInteger("aGropuSeq", grpSeqNextVal);
	        							query.setString("aAuthRef", authRef);
	        							
	        							int updateNextGroupStatusResult = query.executeUpdate();
	        							System.out.println("No of Group Status optional Records updated are : " + updateNextGroupStatusResult);
	        							
	        							// Updating next Auth Status for optional supervisors to P from null and their latest recieved time
	        							String updateNextAuthStatus = "update AuthUIStatusD set authStatus = :aAuthStatus, recTime = :aRecTime where groupSeq= :aGropuSeq and authRef= :aAuthRef and supMan= :aSupMan";
	        							query = sess.createQuery(updateNextAuthStatus);
	        							
	        							query.setString("aAuthStatus","P");
	        							query.setDate("aRecTime",new Date());
	        							query.setInteger("aGropuSeq", grpSeqNextVal);
	        							query.setString("aAuthRef", authRef);
	        							query.setInteger("aSupMan", 0);
	        							
	        							int updateNextGroupAuthStatusResult = query.executeUpdate();
	        							System.out.println("No of Records updated for Optional Supervisor are : " + updateNextGroupAuthStatusResult);

	        							// Fetching the first Mandatory Supervisor in next group
	        							List getFirstManSupSeq = getHibernateTemplate().find("select min(supSeq) from com.logica.ngph.Entity.AuthUIStatusD  where authRef='"+authRef+"' and groupSeq="+grpSeqNextVal+" and supSeq<>0");
    									int firstManSupSeq = new Integer(getFirstManSupSeq.get(0).toString()).intValue();
    									System.out.println("Fisrt Mandatory supervisor in the next Group is : " + firstManSupSeq);
    									
	        							//Updating first mandatory supervisor to P from null their latest recieved time
	        							String updateNextAuthStatusForMand = "update AuthUIStatusD set authStatus = :aAuthStatus, recTime = :aRecTime where groupSeq= :aGropuSeq and authRef= :aAuthRef and supSeq= :aSupSeq";
	        							query = sess.createQuery(updateNextAuthStatusForMand);
	        							
	        							query.setString("aAuthStatus","P");
	        							query.setDate("aRecTime",new Date());
	        							query.setInteger("aGropuSeq", grpSeqNextVal);
	        							query.setString("aAuthRef", authRef);
	        							query.setInteger("aSupSeq", firstManSupSeq);
	        							
	        							int updateNextGroupAuthStatusResultForMand = query.executeUpdate();
	        							System.out.println("No of Records updated for Fisrt Mandatory Supervisor are : " + updateNextGroupAuthStatusResultForMand);

     							}
    							// If the other group does not exists
    							else
    							{
    								System.out.println("Next Group does not exists");
    								
    								// Updating TA_AUTHSTATUST REJECTEDTIME 
    								String updateRejectTimeforT = "update AuthUIStatusT set authTime = :aAuthTime where authRef= :aAuthRef";
        							query = sess.createQuery(updateRejectTimeforT);
        							
        							query.setDate("aAuthTime",new Date());
        							query.setString("aAuthRef", authRef);
        							
        							int updateRejectTimeforTResult = query.executeUpdate();
        							System.out.println("No of Group Status optional Records updated are : " + updateRejectTimeforTResult);
        							
        							// updating tx_Messages
       							 
        							String msgRef = authRef.substring(0, authRef.length()-6);
        							System.out.println("The Message Refrence is : " + msgRef);
        							
        							String msgStatus=null;
        							String prevMsgStatus=null;
        							String direction=null;
        							String newStatus = null;

        							// Fetch prev Msg status and current msgStatus and Direction based on msgRef.
        							
        							List txMsgData = getHibernateTemplate().find("select msgStatus, msgPrevStatus, msgDirection from com.logica.ngph.Entity.NgphCanonical where msgRef='"+msgRef+"'");
									if(txMsgData!=null)
									{
										int DataSize= txMsgData.size();
										
										for (int n = 0; n < DataSize ; n++) {
								            Object[] vData = (Object[]) txMsgData.get(n);
								            
								            msgStatus = vData[0].toString();
								            prevMsgStatus = vData[1].toString();
								            direction = vData[2].toString();
								            
								            System.out.println("Current Message Status : "+ msgStatus);
								            System.out.println("Previous Message Status : "+ prevMsgStatus);
								            System.out.println("Direction of the Payment : "+ direction);

										}
									}
									// Checking the direction of the payment
									if(direction.equalsIgnoreCase("I"))
									{
										//newStatus = PaymentStatusEnum.findPaymentStatusEnumByName("AWAITING_REPAIR_I");
										newStatus = "Authorized";
									}
									else
									{
										//newStatus = PaymentStatusEnum.findPaymentStatusEnumByName("AWAITING_REPAIR");
										newStatus = "Authorized";
									}
									
        							// put msgsts val to prevmsgsts and update mgsts with the new value Awaiting Repair
        							String updateMsgStatusForTxMsg = "update com.logica.ngph.Entity.NgphCanonical set msgStatus = :aMsgStatus and msgPrevStatus = :aMsgPrevStatus and lastModTime= :aLastModTime where msgRef= :aMsgRef";
        							query = sess.createQuery(updateMsgStatusForTxMsg);
        							
        							query.setString("aMsgStatus", newStatus);
        							query.setString("aMsgPrevStatus", msgStatus);
        							query.setDate("aLastModTime", new Date());
        							query.setString("aMsgRef", msgRef);
        							
        							int updateTxMsg= query.executeUpdate();
        							System.out.println("No of Records updated are : " + updateTxMsg);
        							
        							// Raise Event waiting for Modular approach and Pass the message to Repair Queue.
        							//TODO
         							
    							}
    						}
    						}
    						// Supervisor is Mandatory
    						else
    						{
    							System.out.println("Supervisor is mandatory");
    							
 	    							
	    							// Updating optional sup auth status and its auth time
	    							String hql = "update com.logica.ngph.Entity.AuthUIStatusD set authStatus = :aStatus, authTime = :aTime where supId= :aSupId and groupSeq= :aGroupSeq and authRef= :aAuthRef";
	    							query = sess.createQuery(hql);
	    							
	    							query.setString("aStatus","A");
	    							query.setDate("aTime",new Date());
	    							query.setString("aSupId",supId);
	    							query.setInteger("aGroupSeq", grpSeq);
	    							query.setString("aAuthRef", authRef);
	    							int result=query.executeUpdate();
	    							
	    							System.out.println("No of records updated are : " + result);
     							
    							// Check whether all supervisors in a group are optional or not
    							boolean checkisMan = false;
    							Iterator itr = manChecker.iterator();
    							while(itr.hasNext())
    							{
    								String value = itr.next().toString();
    								if(value.matches("0"))
    								{
    									//System.out.println(value);
    									checkisMan=true;
    								}
    								else
    								{
    									checkisMan=false;
    								}
    							}
    							// There is a mandatory supervisor also in a group
    							if(checkisMan==false)
    							{
    								System.out.println("There are other mandatory supervisor in the group");
    								
    								int nextManSupSeq =(int)supSeq + 1;
    								System.out.println("Next Mandatory Supervisor Id is : " + nextManSupSeq);
        								
    								// Update Next Mandatory Supervisor to 'P" where status was null
        							String updatenextManSup = "update AuthUIStatusD set authStatus = :aStatus, recTime = :aRecTime where groupSeq= :aGropuSeq and supSeq= :aSupSeq and authRef= :aAuthRef";
        							query = sess.createQuery(updatenextManSup);
        							
        							query.setString("aStatus","P");
        							query.setDate("aRecTime",new Date());
        							query.setInteger("aGropuSeq", grpSeq);
        							query.setInteger("aSupSeq",nextManSupSeq);
        							query.setString("aAuthRef", authRef);
        							int nextSupManIDResult = query.executeUpdate();
        							System.out.println("No of optional Records updated are : " + nextSupManIDResult);
         					}
    							
    							//All the other supervisor are optional in a group
    							else
    							{
    								System.out.println("All other supervisors are optional in the group");
        								
    								// Update Optional Supervisors as 'D' where status was 'P'
        							String updateOtherOptSup = "update AuthUIStatusD set authStatus = :aStatus, authTime = :aTime where groupSeq= :aGropuSeq and authStatus= :aAuthStatus and authRef= :aAuthRef";
        							query = sess.createQuery(updateOtherOptSup);
        							
        							query.setString("aStatus","D");
        							query.setDate("aTime",new Date());
        							query.setInteger("aGropuSeq", grpSeq);
        							query.setString("aAuthStatus","P");
        							query.setString("aAuthRef", authRef);
        							int updateOtherOptSupResult = query.executeUpdate();
        							System.out.println("No of optional Records updated are : " + updateOtherOptSupResult);
        							
        							// Updating the Group Status to 'A' for all the supervisors in a group
        							String updateGroupStatus = "update AuthUIStatusD set groupAuthStatus = :agroupAuthStatus where groupSeq= :aGropuSeq and authRef= :aAuthRef";
        							query = sess.createQuery(updateGroupStatus);
        							
        							query.setString("agroupAuthStatus","A");
        							query.setInteger("aGropuSeq", grpSeq);
        							query.setString("aAuthRef", authRef);
        							
        							int updateGroupStatusResult = query.executeUpdate();
        							System.out.println("No of Group Status optional Records updated are : " + updateGroupStatusResult);
     							// Check if Other Group Exists or not
    							int grpSeqNextVal = (int)grpSeq + 1;
    							if(groupInfo.get(grpSeqNextVal+"")!=null)
    							{
    								System.out.println("Next Group Sequence is present");
    								System.out.println("Next group Sequence is : " + grpSeqNextVal);
    								
    								// Updating the next Group status to P from null
    								String updateNextGroupStatus = "update AuthUIStatusD set groupAuthStatus = :agroupAuthStatus where groupSeq= :aGropuSeq and authRef= :aAuthRef";
        							query = sess.createQuery(updateNextGroupStatus);
        							
        							query.setString("agroupAuthStatus","P");
        							query.setInteger("aGropuSeq", grpSeqNextVal);
        							query.setString("aAuthRef", authRef);
        							
        							int updateNextGroupStatusResult = query.executeUpdate();
        							System.out.println("No of Group Status optional Records updated are : " + updateNextGroupStatusResult);
        							
        							// Updating next Auth Status for optional supervisors to P from null and their latest recieved time
        							String updateNextAuthStatus = "update AuthUIStatusD set authStatus = :aAuthStatus, recTime = :aRecTime where groupSeq= :aGropuSeq and authRef= :aAuthRef and supMan= :aSupMan";
        							query = sess.createQuery(updateNextAuthStatus);
        							
        							query.setString("aAuthStatus","P");
        							query.setDate("aRecTime",new Date());
        							query.setInteger("aGropuSeq", grpSeqNextVal);
        							query.setString("aAuthRef", authRef);
        							query.setInteger("aSupMan", 0);
        							
        							int updateNextGroupAuthStatusResult = query.executeUpdate();
        							System.out.println("No of Records updated for Optional Supervisor are : " + updateNextGroupAuthStatusResult);

        							// Fetching the first Mandatory Supervisor in next group
        							List getFirstManSupSeq = getHibernateTemplate().find("select min(supSeq) from com.logica.ngph.Entity.AuthUIStatusD  where authRef='"+authRef+"' and groupSeq="+grpSeqNextVal+" and supSeq<>0");
									int firstManSupSeq = new Integer(getFirstManSupSeq.get(0).toString()).intValue();
									System.out.println("Fisrt Mandatory supervisor in the next Group is : " + firstManSupSeq);
									
        							//Updating first mandatory supervisor to P from null their latest recieved time
        							String updateNextAuthStatusForMand = "update AuthUIStatusD set authStatus = :aAuthStatus, recTime = :aRecTime where groupSeq= :aGropuSeq and authRef= :aAuthRef and supSeq= :aSupSeq";
        							query = sess.createQuery(updateNextAuthStatusForMand);
        							
        							query.setString("aAuthStatus","P");
        							query.setDate("aRecTime",new Date());
        							query.setInteger("aGropuSeq", grpSeqNextVal);
        							query.setString("aAuthRef", authRef);
        							query.setInteger("aSupSeq", firstManSupSeq);
        							
        							int updateNextGroupAuthStatusResultForMand = query.executeUpdate();
        							System.out.println("No of Records updated for Fisrt Mandatory Supervisor are : " + updateNextGroupAuthStatusResultForMand);
     						}
    							else
    							{
    								System.out.println("Next Group does not exists");
    								
    								// Updating TA_AUTHSTATUST AuthTime 
    								String updateAuthTimeforT = "update AuthUIStatusT set authTime = :aAuthTime where authRef= :aAuthRef";
        							query = sess.createQuery(updateAuthTimeforT);
        							
        							query.setDate("aAuthTime",new Date());
        							query.setString("aAuthRef", authRef);
        							
        							int updateAuthTimeforTResult = query.executeUpdate();
        							System.out.println("No of Group Status optional Records updated are : " + updateAuthTimeforTResult);
        							
        							// updating tx_Messages
          							 
        							String msgRef = authRef.substring(0, authRef.length()-6);
        							System.out.println("The Message Refrence is : " + msgRef);
        							
        							String msgStatus=null;
        							String prevMsgStatus=null;
        							String direction=null;
        							String newStatus = null;

        							// Fetch prev Msg status and current msgStatus and Direction based on msgRef.
        							
        							List txMsgData = getHibernateTemplate().find("select msgStatus, msgPrevStatus, msgDirection from com.logica.ngph.Entity.NgphCanonical where msgRef='"+msgRef+"'");
									if(txMsgData!=null)
									{
										int DataSize= txMsgData.size();
										
										for (int n = 0; n < DataSize ; n++) {
								            Object[] vData = (Object[]) txMsgData.get(n);
								            
								            msgStatus = vData[0].toString();
								            prevMsgStatus = vData[1].toString();
								            direction = vData[2].toString();
								            
								            System.out.println("Current Message Status : "+ msgStatus);
								            System.out.println("Previous Message Status : "+ prevMsgStatus);
								            System.out.println("Direction of the Payment : "+ direction);

										}
									}
									// Checking the direction of the payment
									if(direction.equalsIgnoreCase("I"))
									{
										//newStatus = PaymentStatusEnum.findPaymentStatusEnumByName("AWAITING_REPAIR_I");
										newStatus = "Authorized";
									}
									else
									{
										//newStatus = PaymentStatusEnum.findPaymentStatusEnumByName("AWAITING_REPAIR");
										newStatus = "Authorized";
									}
									
        							// put msgsts val to prevmsgsts and update mgsts with the new value Awaiting Repair
        							String updateMsgStatusForTxMsg = "update com.logica.ngph.Entity.NgphCanonical set msgStatus = :aMsgStatus and msgPrevStatus = :aMsgPrevStatus and lastModTime= :aLastModTime where msgRef= :aMsgRef";
        							query = sess.createQuery(updateMsgStatusForTxMsg);
        							
        							query.setString("aMsgStatus", newStatus);
        							query.setString("aMsgPrevStatus", msgStatus);
        							query.setDate("aLastModTime", new Date());
        							query.setString("aMsgRef", msgRef);
        							
        							int updateTxMsg= query.executeUpdate();
        							System.out.println("No of Records updated are : " + updateTxMsg);
        							
        							// Raise Event waiting for Modular approach and Pass the message to Repair Queue.
        							//TODO
         							
     							}
    								
    							}
    							
    						}
    					}
    				}
    			}
        	// If Auth Time or Rejected time is not null
    		else
    		{	
    			//@ToDO Show Dialog Box, needs to integrate with Kesavan code
    			System.out.println("Auth Time or Reject Time is not Null");
    			System.out.println("The State has been changed hence page needs to be refreshed");
    			response = "refresh";
    		}
    	
            
		}
	
	}// try
	catch (Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		if(sess!=null && fact!=null)
		{
			sess.flush();
			sess.close();
			fact.close();
		}
	}
 return response;	
}// Method Closed
	
	/**
	 * This Method is used to update comments column in TA_AUTHSTATUST Table
	 * The values will be updated based on business logic
	 */
	public String updateNotes(String supId, String authRef,String notes)
	{
		String response = null;
		
		try
		{
			fact=getHibernateTemplate().getSessionFactory();
			sess=fact.openSession();
			
		// Fetching the Highest AuthCycle from TA_AUTHSTATUST table
		List val = getHibernateTemplate().find("select max(authCycle) as AuthCycle from com.logica.ngph.Entity.AuthUIStatusT where authRef=?",authRef);
		
		Integer j = new Integer(val.get(0).toString());
		int maxAuthCyle = (int) j;
		System.out.println("Highest Auth Cycle in Db is : " + maxAuthCyle);
		
		
		// Checking for Auth Time, Reject Time and comments are null or not
		List dataVals = getHibernateTemplate().find("select authTime, rejectedTime, comments from com.logica.ngph.Entity.AuthUIStatusT where authRef=? and authCycle=?",authRef,maxAuthCyle+"");

		for(int i=0;i<dataVals.size();i++)
		{
			 Object[] obj = (Object[]) dataVals.get(i);
			 
			 System.out.println("Auth Time : " + obj[0] ) ;
			 System.out.println("Rejected Time  : " + obj[1] ) ;
			 System.out.println("Comments : " + obj[2] ) ;
		
		
		//If AuthTime and rejected time is Null
		if(obj[0]==null && obj[1]==null )
		{
			System.out.println("Auth Time and Rejected time is Null");
			// If comments are null
			if(obj[2]==null)
			{
				System.out.println("Comments are null");
				/** update comments column value
				 * Here there is no need to check concurrency
				 * Since if two users are coming when comments column is null
				 * if first updates then automatically for second user the condition will fail
				 * Hence second user will not enter into this condition
				 */
					String hql = "update AuthUIStatusT set comments = :com where authRef = :aRef and authCycle=:aCycle";
					query = sess.createQuery(hql);
					query.setString("com",notes);
					query.setString("aRef",authRef);
					query.setString("aCycle",maxAuthCyle+"");
			        
					int result=query.executeUpdate();
					System.out.println("Comments are null, Update value is : " + result);
					
					response = "sucess";
			}
				//if comments are not null then append to the existing comments and check concurrency
			if(obj[2]!=null)
			{
					System.out.println("Comments are not null");
					// Getting the new comments value
					String oldCommentsVal= obj[2].toString();
					
					StringBuilder newCommentsVal = new StringBuilder(oldCommentsVal);
					newCommentsVal = newCommentsVal.append("\n<" + supId +"> : <" + notes +">");
					
					System.out.println("Old Comments value is : " + oldCommentsVal);
					System.out.println("New Comments value is : " + newCommentsVal);
					int result=0;
					String hql = "update AuthUIStatusT set comments = :newcomm where comments = :oldcomm and authRef = :aRef and authCycle=:aCycle";
					query = sess.createQuery(hql);
						
					query.setString("newcomm",newCommentsVal.toString());
					query.setString("oldcomm",notes);
					query.setString("aRef",authRef);
					query.setString("aCycle",maxAuthCyle+"");
			        
					result=query.executeUpdate();
					
					System.out.println("Comments present, Update value is : " + result);
				
				// Handling Concurrency
				if(result>0)
				{
					System.out.println("Values Populated for the user, First user has updated the value");
					
					response = "sucess";
				}
				else
				{	// @TODO Response needs to be send to the user..integration
					System.out.println("Values already updated...needs to refresh your page");
					response = "refresh";
					
				}
			}
		}
		// If Auth Time or Rejected time is not null
		else
		{	
			//@ToDO Show Dialog Box, needs to integrate with Kesavan code
			System.out.println("Auth Time or Reject Time is not Null");
			System.out.println("The State has been changed hence page needs to be refreshed");
			
			response = "refresh";
		}
	}// For Loop closed
	}// try
	catch (Exception e) {
		e.printStackTrace();
	}
	finally
	{
		if(sess!=null && fact!=null)
		{

			sess.flush();
			sess.close();
			fact.close();
		}
	}
	return response;
}
}
