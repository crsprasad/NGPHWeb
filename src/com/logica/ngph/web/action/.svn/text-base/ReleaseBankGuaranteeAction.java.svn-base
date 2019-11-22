package com.logica.ngph.web.action;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jboss.util.Base64;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.enums.EnumBgStatus;
import com.logica.ngph.dtos.ReleaseBankGuaranteeDto;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.service.ReleaseBankGuaranteeService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ReleaseBankGuaranteeAction extends ActionSupport implements
ModelDriven<ReleaseBankGuaranteeDto>, SessionAware {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ReleaseBankGuaranteeAction.class);
	private Map<String, Object> session;	
	private ReleaseBankGuaranteeDto bankGuaranteeDto = new ReleaseBankGuaranteeDto();
	private String txnRef;
	private boolean validUserToApprove;
	private String checkForSubmit;
	private String hiddenTxnRef;
	private String saveAction;

	public String getSaveAction() {
		return saveAction;
	}

	public void setSaveAction(String saveAction) {
		this.saveAction = saveAction;
	}

	public String getHiddenTxnRef() {
		return hiddenTxnRef;
	}

	public void setHiddenTxnRef(String hiddenTxnRef) {
		this.hiddenTxnRef = hiddenTxnRef;
	}
	public String getCheckForSubmit() {
		return checkForSubmit;
	}

	public void setCheckForSubmit(String checkForSubmit) {
		this.checkForSubmit = checkForSubmit;
	}

	public boolean isValidUserToApprove() {
		return validUserToApprove;
	}

	public void setValidUserToApprove(boolean validUserToApprove) {
		this.validUserToApprove = validUserToApprove;
	}

	public String getTxnRef() {
		return txnRef;
	}

	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	public ReleaseBankGuaranteeDto getBankGuaranteeDto() {
		return bankGuaranteeDto;
	}

	public void setBankGuaranteeDto(ReleaseBankGuaranteeDto bankGuaranteeDto) {
		this.bankGuaranteeDto = bankGuaranteeDto;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@SkipValidation
	public String displayReleaseBGScreen() {
		try{
			System.out.println("=----from ReleaseBGOpenAction: displayReleaseBGScreen() callled-----");
			return "success";

		}catch (NullPointerException  nullPointerException) {
			AuditServiceUtil.logNullPointerException(nullPointerException, logger);
		}
		catch (ApplicationContextException applicationContextException) {
			AuditServiceUtil.logApplicationException(applicationContextException, logger);
		}
		catch (ClassCastException classCastException) {
			AuditServiceUtil.logClassCastException(classCastException, logger)	;
		}
		catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}

		return "input";
	}

	@SkipValidation
	public String displayBGOpenData() {
		try {
			System.out.println("-----displayBGOpenData------");
			String bgNumber = "";
			if(bankGuaranteeDto.getBgNumber()!=null){
				bgNumber = bankGuaranteeDto.getBgNumber();
			}
			System.out.println("entered bg num:" + bgNumber);
			ReleaseBankGuaranteeDto releaseBankGuaranteeDto = new ReleaseBankGuaranteeDto();
			ReleaseBankGuaranteeService releaseBankGuaranteeService =  (ReleaseBankGuaranteeService)  ApplicationContextProvider.getBean("releaseBankGuaranteeService");

			releaseBankGuaranteeDto = releaseBankGuaranteeService.getReleaseBankGuaranteeData(bgNumber);
			if(releaseBankGuaranteeDto==null){
				addActionMessage("BG Number Does Not Exist.");
				return "input";
			}
			if(releaseBankGuaranteeDto!=null && releaseBankGuaranteeDto.getBgStatus()!=null){
				if(releaseBankGuaranteeDto.getBgStatus()== Integer.parseInt(EnumBgStatus.RELEASED.getValue())){
					addActionMessage("BG Already Released.");
					return "input";
				}				
			}
			bankGuaranteeDto.setBgNumber(releaseBankGuaranteeDto.getBgNumber());
			bankGuaranteeDto.setReleaseReqRef(releaseBankGuaranteeDto.getReleaseReqRef());
			bankGuaranteeDto.setAccountNumber(releaseBankGuaranteeDto.getAccountNumber());
			bankGuaranteeDto.setDateOfRelease(releaseBankGuaranteeDto.getDateOfRelease());
			bankGuaranteeDto.setChargeAmount(releaseBankGuaranteeDto.getChargeAmount());
			bankGuaranteeDto.setBgAmount(releaseBankGuaranteeDto.getBgAmount());			
			bankGuaranteeDto.setBgAmountDesc(releaseBankGuaranteeDto.getBgAmountDesc());
			bankGuaranteeDto.setAcctWithNameAndAddr(releaseBankGuaranteeDto.getAcctWithNameAndAddr());
			bankGuaranteeDto.setAcctWithPartyIdentifier1(releaseBankGuaranteeDto.getAcctWithPartyIdentifier1());
			bankGuaranteeDto.setAcctWithPartyIdentifier2(releaseBankGuaranteeDto.getAcctWithPartyIdentifier2());
			bankGuaranteeDto.setAcctWithPartyIFSC(releaseBankGuaranteeDto.getAcctWithPartyIFSC());
			bankGuaranteeDto.setAcctWithPartyLoc(releaseBankGuaranteeDto.getAcctWithPartyLoc());
			bankGuaranteeDto.setAcctWithNameAndAddr(releaseBankGuaranteeDto.getAcctWithNameAndAddr());
			bankGuaranteeDto.setChargesDetails(releaseBankGuaranteeDto.getChargesDetails());			
			bankGuaranteeDto.setSenderToReceiverInformation(releaseBankGuaranteeDto.getSenderToReceiverInformation());

			return "success";

		}catch (NullPointerException  nullPointerException) {
			AuditServiceUtil.logNullPointerException(nullPointerException, logger);
		}
		catch (ApplicationContextException applicationContextException) {
			AuditServiceUtil.logApplicationException(applicationContextException, logger);
		}
		catch (ClassCastException classCastException) {
			AuditServiceUtil.logClassCastException(classCastException, logger)	;
		}
		catch (Exception exception) {			
			AuditServiceUtil.logException(exception,logger);
			exception.printStackTrace();
		}
		addFieldError("bgNumber","BG Number Does Not Exist.");
		return "input";
	}


	public String submitBankGuaranteeDetails() {
		try {
			System.out.println("-------submitBankGuaranteeDetails--------");
			
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);

			pendingAuthorizationService.getTranRef(serializeObject(),"Release Bank Guarantee",userId);
			return "success";


		} catch (NullPointerException nullPointerException) {
			AuditServiceUtil.logNullPointerException(nullPointerException,
					logger);
		} catch (ApplicationContextException applicationContextException) {
			AuditServiceUtil.logApplicationException(
					applicationContextException, logger);
		} catch (ClassCastException classCastException) {
			AuditServiceUtil.logClassCastException(classCastException, logger);
		} catch (Exception exception) {
			AuditServiceUtil.logException(exception, logger);
		}

		addActionError("Unable to perform the operation. Please try again");
		return "input";
	}	

	public String approveBGDetails() {
		try{
			System.out.println("-------saveBankGuaranteeDetails--------");
			
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);

			if(getSaveAction().equalsIgnoreCase("Approve")){			
				ReleaseBankGuaranteeService releaseBankGuaranteeService =  (ReleaseBankGuaranteeService)  ApplicationContextProvider.getBean("releaseBankGuaranteeService");			
				releaseBankGuaranteeService.releaseOrCreateBankGuarantee(bankGuaranteeDto, userId, getHiddenTxnRef());				
			}else{
				PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());				
			}
			return "success";
		}catch (NullPointerException  nullPointerException) {
			AuditServiceUtil.logNullPointerException(nullPointerException, logger);
			nullPointerException.printStackTrace();
		}
		catch (ApplicationContextException applicationContextException) {
			AuditServiceUtil.logApplicationException(applicationContextException, logger);
			applicationContextException.printStackTrace();
		}
		catch (ClassCastException classCastException) {
			AuditServiceUtil.logClassCastException(classCastException, logger)	;
			classCastException.printStackTrace();
		}
		catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
			exception.printStackTrace();
		}
		addActionError("Unable to perform the operation. Please try again");
		return "input";
	}
	
	@SkipValidation
	public String getReleaseBGAuthorization(){
		try {
			System.out.println("--------getReleaseBGAuthorization----");
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			setCheckForSubmit("Display_Approve_Reject");
			String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
			String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
			if(((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || !userRole.equals("S")){
				setValidUserToApprove(false);
			} else {
				setValidUserToApprove(true);
			}
			
			String tempScreenString =pendingAuthorizationService.getScreenReturn(getTxnRef());
			ReleaseBankGuaranteeDto tempDataHolder= getSerializedObject(tempScreenString);
			
			bankGuaranteeDto.setBgNumber(tempDataHolder.getBgNumber());
			bankGuaranteeDto.setReleaseReqRef(tempDataHolder.getReleaseReqRef());
			bankGuaranteeDto.setAccountNumber(tempDataHolder.getAccountNumber());
			bankGuaranteeDto.setDateOfRelease(tempDataHolder.getDateOfRelease());
			bankGuaranteeDto.setChargeAmount(tempDataHolder.getChargeAmount());			
			bankGuaranteeDto.setBgAmount(tempDataHolder.getBgAmount());
			bankGuaranteeDto.setBgAmountDesc(tempDataHolder.getBgAmountDesc());
			bankGuaranteeDto.setAcctWithPartyIdentifier1(tempDataHolder.getAcctWithPartyIdentifier1());
			bankGuaranteeDto.setAcctWithPartyIdentifier2(tempDataHolder.getAcctWithPartyIdentifier2());
			bankGuaranteeDto.setAcctWithPartyIFSC(tempDataHolder.getAcctWithPartyIFSC());
			bankGuaranteeDto.setAcctWithPartyLoc(tempDataHolder.getAcctWithPartyLoc());
			bankGuaranteeDto.setBgAmount(tempDataHolder.getBgAmount());
			bankGuaranteeDto.setAcctWithNameAndAddr(tempDataHolder.getAcctWithNameAndAddr());
			bankGuaranteeDto.setChargesDetails(tempDataHolder.getChargesDetails());
			bankGuaranteeDto.setSenderToReceiverInformation(tempDataHolder.getSenderToReceiverInformation());

			setHiddenTxnRef(getTxnRef());

			return "success";
		} catch (NullPointerException  nullPointerException) {
			AuditServiceUtil.logNullPointerException(nullPointerException, logger);
		}
		catch (ApplicationContextException applicationContextException) {
			AuditServiceUtil.logApplicationException(applicationContextException, logger);
		}
		catch (ClassCastException classCastException) {
			AuditServiceUtil.logClassCastException(classCastException, logger)	;
		}
		catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}

		addActionError("Unable to perform the operation. Please try again");
		return "input";
	}
	public String serializeObject()
	{
		ReleaseBankGuaranteeDto releaseBgDto = new ReleaseBankGuaranteeDto();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);

			releaseBgDto = bankGuaranteeDto;
			System.out.print("BG NUMBER :"+releaseBgDto.getBgNumber());
			String fileName ="serial_"+userId+".ser";
			FileOutputStream fos = new FileOutputStream(fileName);
			OutputStream buffer = new BufferedOutputStream( fos );
			ObjectOutputStream oos = new ObjectOutputStream(buffer);
			oos.writeObject(releaseBgDto);
			oos.flush();
			oos.close();
			File file = new File(fileName);
			byte[] byteArray = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file); 
			fis.read(byteArray);
			String objectString = Base64.encodeBytes(byteArray);
			//System.out.print("Object String :"+objectString);

			return objectString;


		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private ReleaseBankGuaranteeDto getSerializedObject(String objectString){

		try{

			byte[] decoded = Base64.decode(objectString);

			FileOutputStream foss = new FileOutputStream("targetUserObject.ser");
			foss.write(decoded);
			foss.close();
			ReleaseBankGuaranteeDto testDTO = null;

			FileInputStream fiss = new FileInputStream("targetUserObject.ser");
			BufferedInputStream bufferee = new BufferedInputStream( fiss );
			ObjectInputStream oiss = new ObjectInputStream(bufferee);
			testDTO = (ReleaseBankGuaranteeDto)oiss.readObject();
			oiss.close();
			System.out.println("object2: " + testDTO); 
			System.out.print("User(testDTO) :"+testDTO.getBgNumber());

			return testDTO;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ReleaseBankGuaranteeDto getModel() {
		return bankGuaranteeDto;
	}

}
