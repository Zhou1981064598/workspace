function TraceInfoFlag() {
    this.traceFlag = new Array();
    this.traceFlag["cgex.c:43c27"]=1;
    this.traceFlag["cgex.c:43c39"]=1;
}
top.TraceInfoFlag.instance = new TraceInfoFlag();
function TraceInfoLineFlag() {
    this.lineTraceFlag = new Array();
    this.lineTraceFlag["cgex.c:43"]=1;
}
top.TraceInfoLineFlag.instance = new TraceInfoLineFlag();
