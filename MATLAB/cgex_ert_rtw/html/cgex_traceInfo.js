function RTW_rtwnameSIDMap() {
	this.rtwnameHashMap = new Array();
	this.sidHashMap = new Array();
	this.rtwnameHashMap["<Root>"] = {sid: "cgex"};
	this.sidHashMap["cgex"] = {rtwname: "<Root>"};
	this.rtwnameHashMap["<Root>/Input"] = {sid: "cgex:6"};
	this.sidHashMap["cgex:6"] = {rtwname: "<Root>/Input"};
	this.rtwnameHashMap["<Root>/Input1"] = {sid: "cgex:7"};
	this.sidHashMap["cgex:7"] = {rtwname: "<Root>/Input1"};
	this.rtwnameHashMap["<Root>/Gain"] = {sid: "cgex:4"};
	this.sidHashMap["cgex:4"] = {rtwname: "<Root>/Gain"};
	this.rtwnameHashMap["<Root>/Sum"] = {sid: "cgex:3"};
	this.sidHashMap["cgex:3"] = {rtwname: "<Root>/Sum"};
	this.rtwnameHashMap["<Root>/Out1"] = {sid: "cgex:5"};
	this.sidHashMap["cgex:5"] = {rtwname: "<Root>/Out1"};
	this.getSID = function(rtwname) { return this.rtwnameHashMap[rtwname];}
	this.getRtwname = function(sid) { return this.sidHashMap[sid];}
}
RTW_rtwnameSIDMap.instance = new RTW_rtwnameSIDMap();
