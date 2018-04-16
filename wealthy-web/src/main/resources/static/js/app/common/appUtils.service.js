var module = angular.module('serviceUtils',[]);

module.service('AppMessageService', [function(){
	var messages = [
		 {code:"CRUD1", type:'OK', content:"You successfully created the "},
		 {code:"CRUD1", type:'ERROR', content:"Something went wrong, we cannot create the "},
//		 {code:"CRUD2", type:'OK', content:"You successfully read the "},
//		 {code:"CRUD2", type:'ERROR', content:"Something went wrong we read get the "},
		 {code:"CRUD3", type:'OK', content:"You successfully updated the "},
		 {code:"CRUD3", type:'ERROR', content:"Something went wrong we cannot update the "},
		 {code:"CRUD4", type:'OK', content:"You successfully deleted the "},
		 {code:"CRUD4", type:'ERROR', content:"Something went wrong we cannot delete the "}
	  ]
	
	function getContent(type, code){
		var message = _.filter(messages, {'type': type, 'code': code});
		if(message.length === 1){
			return message[0].content;
		}
		else{
			return 'Sorry, somenthing is wrong with our service';
		}
	}
	
	function displayDefaultMessage(code, type, elementName){
		return {
				display: true,
				type: type,
				content: getContent(type, code) + elementName
		}
	}	

	function displayMessageOverrideContent(msg, type, content){
		msg.display = true;
		msg.type = type; 
		msg.content = content;
	}	
	
	return {
		displayDefaultMessage: displayDefaultMessage,
		displayMessageOverrideContent: displayMessageOverrideContent
	}
	
}]);