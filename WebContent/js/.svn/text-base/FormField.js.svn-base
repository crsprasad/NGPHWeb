//javascript method to Not allow special char to type
function notAllowCheck(field,e){
	if (!e) var e = window.event
	if (e.keyCode) code = e.keyCode;
	else if (e.which) code = e.which;
	var character = String.fromCharCode(code);
	if (code==27) { this.blur(); return false; }
	if (!e.ctrlKey && code!=36 && code!=37 && code!=38 && code!=42 && code!=95 && code!=35 && code!=124 
			&& code!=94 && code!=64 && code!=33 && code!=34 && code!=123 && code!=125 && code !=59 
			&& code!=91 && code!=93 && code!=60 && code!=62 && code!=61 && code!=92 && code!=126) {
				return true;
		} else {
			return false;
		}
}
//javascript method to check not allow char with length for TextArea
function maxLength(field, maxChars,fieldName,rowLength,e) {
	if (field.value.length >= maxChars) {
		event.returnValue = false;
		return false;
	}else
	{
		if (!e) var e = window.event
		if (e.keyCode) code = e.keyCode;
		else if (e.which) code = e.which;
		var character = String.fromCharCode(code);
		if (code==27) { this.blur(); return false; }
		if (!e.ctrlKey && code!=36 && code!=37 && code!=38 && code!=42 && code!=95 && code!=35 && code!=124 
				&& code!=94 && code!=64 && code!=33 && code!=34 && code!=123 && code!=125 && code !=59 
				&& code!=91 && code!=93 && code!=60 && code!=62 && code!=61 && code!=92 && code!=126) {
			//countLine(maxChars,fieldName,rowLength);
						return true;
					} else {
						return false;
					}
		
		}

}
function countLine(maxChars,id,rowLength) {
	var value = document.getElementById(id).value;
	//id.innerHTML = value.substr(0, textarea.selectionStart).split("\n").length;
	if ((value.length) > 0 && (value.length) % (rowLength) == 0
			&& (value.length) < maxChars) {

		value = value + "\n";
				
	} else {
		
	}

	document.getElementById(id).value = value;
}


function limitTextarea(textarea, maxLines, maxChar) {
    var lines = textarea.value.replace(/\r/g, '').split('\n'), lines_removed, char_removed, i;
    if (maxLines && lines.length > maxLines) {
        lines = lines.slice(0, maxLines);
        lines_removed = 1;
    }
    if (maxChar) {
        i = lines.length;
        while (i-- > 0) if (lines[i].length > maxChar) {
            lines[i] = lines[i].slice(0, maxChar);
            char_removed = 1;
        }
        if (char_removed || lines_removed) {
            textarea.value = lines.join('\n');
        }
    }
}

function updatedTextArea(row,id){
	var value = document.getElementById(id);
	if(value == null){
		return false;
	}
	else{
		var prevBenifVal = document.getElementById(id).value;
		var temp = ""; 
		var rowCount = 0;
		var tem1 ="";
		//alert(prevBenifVal); 
		if(prevBenifVal.indexOf("\r\n") != -1)
			{
			var lines =prevBenifVal.split("\n");
			for(j=0;j<lines.length;j++){
				if(lines[j].length<=row)
						{
						tem1 = tem1 + lines[j];
						}else {
							for(i=0;i<lines[j].length;i++){
							rowCount++;
							if(rowCount==row)
								{
								tem1 = 	tem1 + lines[j].charAt(i) + "\r\n";
									rowCount = 0;
								}
							else
								tem1 = tem1 + lines[j].charAt(i);

						}
					
				}
			}
			temp = temp +tem1;
	}else if(prevBenifVal.indexOf("\r\n") == -1){
				prevBenifVal = prevBenifVal.replace("\r\n","");
				for(i=0;i<=prevBenifVal.length;i++)
					{
						rowCount++;
						if(rowCount==row)
							{
								temp = 	temp + prevBenifVal.charAt(i) + "\r\n";
								rowCount = 0;
							}
						else
							temp = temp + prevBenifVal.charAt(i);
						
					}
			}
	//	alert(temp);
		document.getElementById(id).value = temp;
	}
}
