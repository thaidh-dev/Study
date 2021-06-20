using System;
using System.Collections.Generic;
using System.Text;

namespace API_Consumer
{
    class DataShape
    {
        public DataShape()
        {
            columnMetas = new List<KylinColumn>();
            results = new List<List<string>>();
        }
        public List<KylinColumn> columnMetas { get; set; }
        public List<List<string>> results { get; set; }

    }
}
