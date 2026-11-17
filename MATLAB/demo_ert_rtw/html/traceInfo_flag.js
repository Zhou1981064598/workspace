function TraceInfoFlag() {
    this.traceFlag = new Array();
    this.traceFlag["demo.c:30c50"]=1;
}
top.TraceInfoFlag.instance = new TraceInfoFlag();
function TraceInfoLineFlag() {
    this.lineTraceFlag = new Array();
    this.lineTraceFlag["demo.c:30"]=1;
}
top.TraceInfoLineFlag.instance = new TraceInfoLineFlag();
