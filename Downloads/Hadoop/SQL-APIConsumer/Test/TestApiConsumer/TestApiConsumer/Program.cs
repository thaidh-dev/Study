using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace TestApiConsumer
{
    class Program
    {
        static void Main(string[] args)
        {
            string thaidh = "{\"columnMetas\":[{\"isNullable\":1,\"displaySize\":250,\"label\":\"SITENAME\",\"name\":\"SITENAME\",\"schemaName\":\"MDO\",\"catelogName\":null,\"tableName\":\"DIM_CELL_INFO_CELLS_CURRENT\",\"precision\":250,\"scale\":0,\"columnType\":12,\"columnTypeName\":\"VARCHAR\",\"readOnly\":true,\"autoIncrement\":false,\"caseSensitive\":true,\"searchable\":false,\"currency\":false,\"signed\":true,\"writable\":false,\"definitelyWritable\":false},{\"isNullable\":1,\"displaySize\":255,\"label\":\"DISTRICT\",\"name\":\"DISTRICT\",\"schemaName\":\"MDO\",\"catelogName\":null,\"tableName\":\"DIM_CELL_INFO_CELLS_CURRENT\",\"precision\":255,\"scale\":0,\"columnType\":12,\"columnTypeName\":\"VARCHAR\",\"readOnly\":true,\"autoIncrement\":false,\"caseSensitive\":true,\"searchable\":false,\"currency\":false,\"signed\":true,\"writable\":false,\"definitelyWritable\":false}],\"results\":[[\"H07096\",\"Q.7\"],[\"H07165\",\"Q.7\"],[\"LU_LCU_SAN_THANG\",\"Lai Chau\"],[\"VUTT72\",\"Tan Thanh\"],[\"PYDX12\",\"Dong Xuan\"],[\"HTD086\",\"Thu Duc\"],[\"H07061\",\"Q.7\"]],\"cube\":\"CUBE[name = HTTPS]\",\"affectedRowCount\":0,\"isException\":false,\"exceptionMessage\":null,\"duration\":1640,\"totalScanCount\":0,\"totalScanBytes\":0,\"hitExceptionCache\":false,\"storageCacheUsed\":false,\"traceUrl\":null,\"partial\":false,\"pushDown\":false}";
            //Console.WriteLine(thaidh);

            var xxy = new List<Table>();
            KylinResponse stuff = JsonConvert.DeserializeObject<KylinResponse>(thaidh);
            for (int x = 0; x < stuff.results.Count; x++)
            {
                var map = new Table();
                map.col1 = stuff.results[x][0];
                map.col2 = stuff.results[x][1];

                xxy.Add(map);
            }
            //Console.WriteLine(JsonConvert.SerializeObject(xxy));

            string sql = "select datetime_id, date_id from dim_date_time;";
            string offset = "0";
            string limit = "7";
            string acceptPartial = "true";
            string project = "abc";
            string JsonBody = "{\"sql\":\"" + sql.ToString() + "\"," +
                "\"offset\":" + (offset == null ? "0" : offset.ToString()) + "," +
                "\"limit\":" + (limit == null ? "0" : limit.ToString()) + "," +
                "\"acceptPartial\":" + (acceptPartial == null ? "false" : acceptPartial.ToString()) + "," +
                "\"project\":\"" + (project == null ? "default" : project.ToString()) + "\"}";

            string JsonBody5 = "{\"sql\":\"" + sql.ToString() + "\"," +
                "\"offset\":" + (offset == null ? "0" : offset.ToString()) + "," +
                "\"limit\":" + (limit == null ? "0" : limit.ToString()) + "," +
                "\"acceptPartial\":" + (acceptPartial == null ? "false" : acceptPartial.ToString()) + "," +
                "\"project\":\"" + (project == null ? "default" : project.ToString()) + "\"}";

            string JsonBody2 = "{\"sql\":\"" + sql.ToString() + "\",\"offset\":" + offset == null ? "0" : offset.ToString() + ",\"limit\":" + limit == null ? "0" : limit.ToString() + ",\"acceptPartial\":" + acceptPartial == null ? "false" : acceptPartial.ToString() + ",\"project\":\"" + project == null ? "default" : project.ToString() + "\"}";

            string JsonBody3 = "{\"sql\":\"" + sql.ToString() + "\",\"offset\":" + (offset == null ? "0" : offset.ToString());

            Console.WriteLine(JsonBody);

            string username = "ADMIN";
            string pass = "KYLIN";
            string encoded = System.Convert.ToBase64String(System.Text.Encoding.GetEncoding("ISO-8859-1").GetBytes(username.ToString() + ":" + pass.ToString()));
            string JsonHeader = "[{\"Content - Type\":\"application / json; charset = utf - 8\"},{\"Authorization\":\"Basic " + encoded + " = \"}]";
            Console.WriteLine(JsonHeader);

        }
    }
}
