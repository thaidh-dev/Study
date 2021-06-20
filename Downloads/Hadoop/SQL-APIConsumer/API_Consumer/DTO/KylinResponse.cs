using System;
using System.Collections.Generic;
using System.Text;

namespace API_Consumer
{
    public class KylinResponse
    {
        public Column[] columnMetas { get; set; }
        public List<List<string>> results { get; set; }
        public string cube { get; set; }
        public int affectedRowCount { get; set; }
        public bool isException { get; set; }
        public string exceptionMessage { get; set; }
        public int duration { get; set; }
        public int totalScanCount { get; set; }
        public int totalScanBytes { get; set; }
        public bool hitExceptionCache { get; set; }
        public bool storageCacheUsed { get; set; }
        public string traceUrl { get; set; }
        public bool partial { get; set; }
        public bool pushDown { get; set; }
    }
}
