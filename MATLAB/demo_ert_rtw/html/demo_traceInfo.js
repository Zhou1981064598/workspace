function RTW_rtwnameSIDMap() {
	this.rtwnameHashMap = new Array();
	this.sidHashMap = new Array();
	this.rtwnameHashMap["<Root>"] = {sid: "demo"};
	this.sidHashMap["demo"] = {rtwname: "<Root>"};
	this.rtwnameHashMap["<Root>/Constant"] = {sid: "demo:1"};
	this.sidHashMap["demo:1"] = {rtwname: "<Root>/Constant"};
	this.rtwnameHashMap["<Root>/Constant1"] = {sid: "demo:4"};
	this.sidHashMap["demo:4"] = {rtwname: "<Root>/Constant1"};
	this.rtwnameHashMap["<Root>/Sum"] = {sid: "demo:3"};
	this.sidHashMap["demo:3"] = {rtwname: "<Root>/Sum"};
	this.rtwnameHashMap["<Root>/Out1"] = {sid: "demo:7"};
	this.sidHashMap["demo:7"] = {rtwname: "<Root>/Out1"};
	this.getSID = function(rtwname) { return this.rtwnameHashMap[rtwname];}
	this.getRtwname = function(sid) { return this.sidHashMap[sid];}
}
RTW_rtwnameSIDMap.instance = new RTW_rtwnameSIDMap();
