﻿using System;
using System.Collections.Generic;
using System.Text;

namespace TestApiConsumer
{
    class KylinResponse
    {
        public Column[] columnMetas { get; set; }
        public List<List<string>> results { get; set; }
        public string cube { get; set; }
        public int affectedRowCount { get; set; }
        public bool isException { get; set; }
        public string exceptionMessage { get; set; }
        public int duration { get; set; }
        public bool partial { get; set; }
    }
}
