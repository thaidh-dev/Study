using System;
using System.Collections.Generic;
using System.Text;

namespace API_Consumer
{
    public class Column
    {
        public int isNullable { get; set; }
        public int displaySize { get; set; }
        public string label { get; set; }
        public string name { get; set; }
        public string schemaName { get; set; }
        public string catelogName { get; set; }
        public string tableName { get; set; }
        public int precision { get; set; }
        public int scale { get; set; }
        public int columnType { get; set; }
        public string columnTypeName { get; set; }
        public bool readOnly { get; set; }
        public bool writable { get; set; }
        public bool caseSensitive { get; set; }
        public bool searchable { get; set; }
        public bool currency { get; set; }
        public bool signed { get; set; }
        public bool autoIncrement { get; set; }
        public bool definitelyWritable { get; set; }
    }
}
