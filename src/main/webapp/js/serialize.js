(function($){  
        $.fn.serializeJson=function(){  
            var serializeObj={};  
            var array=this.serializeArray();  
            var str=this.serialize();  
            $(array).each(function(){  
                if(serializeObj[this.name]){  
                    if($.isArray(serializeObj[this.name])){  
                        serializeObj[this.name].push(this.value);  
                    }else{  
                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
                    }  
                }else{  
                    serializeObj[this.name]=this.value;   
                }  
            });  
            return serializeObj;  
        };  
    })(jQuery);
(function($){  
    $.fn.serializeForm=function(formid){  

    	//可编辑表格中的:input去除
		var o = {};
        $("#" + formid + " :input").each(function(){
            var n = $(this).attr("name");
            if(n){
	            if(this.type=="checkbox"){
	            	if(!o[n]){o[n]=[];}
	            	if(this.checked){
	            		o[n].push(this.value);
	            	}
	            }else if(this.type=="radio"){
	            	if(!o[n]){o[n]="";}
	            	if(this.checked){
	            		o[n]=this.value;
	            	}
	            }else if (this.tagName=="select"){
	            	o[n]=this.options[this.selectedIndex].value;
	            }else{
	            	o[n]=this.value;
	            	/*
	            	if(this.type=="text" && this.getAttribute("numberformat")){//金额输入字段，去掉,
	            		o[n]=this.value.replace(/\,/gi,"");
	            	}*/
	            }
            }
        });
        //join array value
        for(var k in o){
        	if(o[k].constructor && o[k].constructor==Array){
        		o[k]=o[k].join(",");
        	}
        }
        return o;
    };  
})(jQuery);


	/** 
	**json对象数据设置到表单域中 
	*/ 
function jsonObjectToForm(form, jsonObject){
	for(i = 0, max = form.elements.length; i < max; i++) { 
		e = form.elements[i]; 
		eName = e.name; 
		if(eName.indexOf('.') > 0){ 
			dotIndex = eName.indexOf('.'); 
			parentName = eName.substring(0, dotIndex); 
			childName = eName.substring(dotIndex+1); 
			//迭代判断eName，组装成json数据结构 
			eValue = iterValueFromJsonObject(jsonObject, parentName, childName); 
		}else{ 
			eValue = jsonObject[eName]; 
		} 
		if(eValue && eValue != "undefined" && eValue != "null"){ 
			switch(e.type){ 
			case 'checkbox': 
			case 'radio': 
				if(e.value == eValue){ 
					e.checked = true; 
				} 
				break; 
			case 'hidden': 
			case 'password': 
			case 'textarea': 
			case 'text': 
				e.value = eValue; 
				break; 
			case 'select-one': 
			case 'select-multiple': 
				for(var j = 0; j < e.options.length; j++){ 
					op = e.options[j]; 
					//alert("eName : " + eName + "; op value : " + op.value + "; eValue : " + eValue); 
					if(op.value == eValue){ 
						op.selected = true; 
					} 
				} 
				break; 
			case 'button': 
			case 'file': 
			case 'image': 
			case 'reset': 
			case 'submit': 
			default: 
			} 
		} 
	} 
} 

/** 
 * json数组读写有两种方式 
 * 1: a.bs[0].id 
 * 2: a["bs"][0]["id"] 
 * 把表单转换成json数据格式 
 */ 
 function formToJsonObject(form){ 
    var jsonObject = {};
    for(i = 0, max = form.elements.length; i < max; i++) { 
	    e = form.elements[i]; 
	    em = new Array(); 
	    if(e.type == 'select-multiple'){ 
		    for(var j = 0; j < e.options.length; j++){ 
			    op = e.options[j]; 
			    if(op.selected){ 
			    	em[em.length] = op.value; 
		    	} 
		    } 
	    } 
    switch(e.type){ 
	    case 'checkbox': 
	    case 'radio': 
	    if (!e.checked) { break; } 
	    case 'hidden': 
	    case 'password': 
	    case 'select-one': 
	    case 'select-multiple': 
	    case 'textarea': 
	    case 'text': 
	    eName = e.name; 
	    if(e.type == 'select-multiple'){ 
	    eValue = em; 
	    }else{ 
	    eValue = e.value.replace(new RegExp('(["\\\\])', 'g'), '\\$1').trim(); 
	    } 
	    //判断是否是对象类型数据 
	    if(eName.indexOf('.') > 0){ 
	    dotIndex = eName.indexOf('.'); 
	    parentName = eName.substring(0, dotIndex); 
	    childName = eName.substring(dotIndex+1); 
	    //迭代判断eName，组装成json数据结构 
	    iterJsonObject(jsonObject, parentName, childName, eValue); 
	    }else{
	    	if(eName != "") {
	    		jsonObject[eName] = eValue; 
	    	}
	    } 
	    break; 
	    case 'button': 
	    case 'file': 
	    case 'image': 
	    case 'reset': 
	    case 'submit': 
	    default: 
    } 
    } 
    return jsonObject; 
 } 

 /** 
 * 把表单元素迭代转换成json数据 
 */ 
 function iterJsonObject(jsonObject, parentName, childName, eValue){ 
	    //pArrayIndex用于判断元素是否是数组标示 
	    pArrayIndex = parentName.indexOf('['); 
	    //判断是否集合数据，不是则只是对象属性 
	    if(pArrayIndex < 0){ 
	    var child = jsonObject[parentName]; 
	    if(!child){ 
	    jsonObject[parentName] = {}; 
	    } 
	    dotIndex = childName.indexOf('.'); 
	    if(dotIndex > 0){ 
	    iterJsonObject(jsonObject[parentName], childName.substring(0, dotIndex), childName.substring(dotIndex+1), eValue); 
	    }else{ 
	    jsonObject[parentName][childName] = eValue; 
	    } 
	    }else{ 
	    pArray = jsonObject[parentName.substring(0, pArrayIndex)]; 
	    //若不存在js数组，则初始化一个数组类型 
	    if(!pArray){ 
	    jsonObject[parentName.substring(0, pArrayIndex)] = new Array(); 
	    } 
	    //取得集合下标，并判断对应下标是否存在js对象 
	    arrayIndex = parentName.substring(pArrayIndex+1, parentName.length-1); 
	    var c = jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex]; 
	    if(!c){ 
	    jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex] = {}; 
	    } 
	    dotIndex = childName.indexOf('.'); 
	    if(dotIndex > 0){ 
	    iterJsonObject(jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex], childName.substring(0, dotIndex), childName.substring(dotIndex+1), eValue); 
	    }else{ 
	    jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex][childName] = eValue; 
	    } 
	    } 
 }
 
 /** 
 * 迭代json数据对象设置到表单域中 
 */ 
 function iterValueFromJsonObject(jsonObject, parentName, childName){ 
	 //pArrayIndex用于判断元素是否是数组标示 
	 pArrayIndex = parentName.indexOf('['); 
	 //判断是否集合数据，不是则只是对象属性 
	 if(pArrayIndex < 0){
		 dotIndex = childName.indexOf('.'); 
		 if(dotIndex > 0){ 
			 return iterValueFromJsonObject(jsonObject[parentName], childName.substring(0, dotIndex), childName.substring(dotIndex+1)); 
		 }else{ 
			 return jsonObject[parentName][childName];
		 }
	 }else{ 
		 pArray = jsonObject[parentName.substring(0, pArrayIndex)]; 
		 //取得集合下标，并判断对应下标是否存在js对象 
		 arrayIndex = parentName.substring(pArrayIndex+1, parentName.length-1); 
		 var c = jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex]; 
		 dotIndex = childName.indexOf('.'); 
		 if(dotIndex > 0){ 
			 return iterValueFromJsonObject(jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex], childName.substring(0, dotIndex), childName.substring(dotIndex+1)); 
		 }else{ 
			 return jsonObject[parentName.substring(0, pArrayIndex)][arrayIndex][childName];
		 } 
	 } 
 }