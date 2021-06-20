//using Microsoft.SqlServer.Server;
//using Newtonsoft.Json;
using API_Consumer;
using Newtonsoft.Json;
using SQLAPI_Consumer;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlTypes;

/// <summary>
/// Generic Get Api Consumer thought CLR Proc
/// </summary>
public partial class StoredProcedures
{
    /// <summary>
    /// It's a generic procedure used to consume Api throught GET method.
    /// Returns the result as a varchar(max). Could be used to return Json.
    /// </summary>
    /// <param name="URL">Api GET Method</param>
    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_GET(SqlString URL)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;

        try
        {
            string Result = APIConsumer.GETMethod(URL.ToString());

            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_RESULT, Result);

        }
        catch (Exception ex)
        {
            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_ERROR, ex.Message.ToString());
            ExecutionResult = APIConsumer.FAILED_EXECUTION_RESULT;
        }

        return ExecutionResult;
    }

    /// <summary>
    /// It's a generic procedure used to consume Api throught GET method.
    /// Returns the result as a varchar(max). Could be used to return Json.
    /// </summary>
    /// <param name="URL">Api GET Method</param>
    /// <param name="Authorization">Authorization Header</param>
    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_GET_Auth(SqlString URL, SqlString Authorization)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;

        try
        {
            string Result = APIConsumer.GETMethod(URL.ToString(), Authorization.ToString());

            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_RESULT, Result);

        }
        catch (Exception ex)
        {
            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_ERROR, ex.Message.ToString());
            ExecutionResult = APIConsumer.FAILED_EXECUTION_RESULT;
        }

        return ExecutionResult;
    }

    /// <summary>
    /// It's a generic procedure used to consume Api throught GET method.
    /// Returns the result as a varchar(max). Could be used to return Json.
    /// </summary>
    /// <param name="URL">Api GET Method</param>
    /// <param name="Headers">Json Headers</param>
    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_GET_Headers(SqlString URL, SqlString Headers)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;

        try
        {
            string Result = APIConsumer.GETMethod_Headers(URL.ToString(), Headers.ToString());

            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_RESULT, Result);

        }
        catch (Exception ex)
        {
            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_ERROR, ex.Message.ToString());
            ExecutionResult = APIConsumer.FAILED_EXECUTION_RESULT;
        }

        return ExecutionResult;
    }

    /// <summary>
    /// It's a generic procedure used to consume Api throught GET method.
    /// Returns the result as a varchar(max). Could be used to return Json.
    /// </summary>
    /// <param name="URL">Api GET Method</param>
    /// <param name="Headers">Json Headers</param>
    /// <param name="JsonBody">Json Body</param>
    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_GET_JsonBody_Header(SqlString URL, SqlString Headers, SqlString JsonBody)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;
        try
        {
            string Result = APIConsumer.GETMethod_Headers(URL.ToString(), JsonBody.ToString(), Headers.ToString());

            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_RESULT, Result);

        }
        catch (Exception ex)
        {
            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_ERROR, ex.Message.ToString());
            ExecutionResult = APIConsumer.FAILED_EXECUTION_RESULT;
        }

        return ExecutionResult;
    }

    /// <summary>
    /// It's a generic procedure used to consume Api throught GET method.
    /// Returns the result as a varchar(max). Could be used to return Json.
    /// Content Type must be suplied as headers
    /// </summary>
    /// <param name="URL">Api GET Method</param>
    /// <param name="Headers">Json Headers</param>
    /// <param name="JsonBody">Json Body</param>
    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_GET_Extended(SqlString URL, SqlString JsonBody, SqlString Headers)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;
        try
        {
            API_Consumer.ExtendedResult ExtResult = new API_Consumer.ExtendedResult();

            string Result = APIConsumer.GETMethod_Extended(ref ExtResult, URL.ToString(), JsonBody.ToString(), Headers.ToString());

            Helper.SendResultValue(ExtResult);

            //  Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_RESULT, Result);
        }
        catch (Exception ex)
        {
            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_ERROR, ex.Message.ToString());
            ExecutionResult = APIConsumer.FAILED_EXECUTION_RESULT;
        }

        return ExecutionResult;
    }

    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_GET_Example(
        SqlString URL,
        SqlString username,
        SqlString pass,
        SqlString sql,
        SqlString offset,
        SqlString limit,
        SqlString acceptPartial,
        SqlString project
    )
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;

        //try
        //{
        string JsonBody = "{\"sql\":\"" + sql.ToString() + "\"," +
            "\"offset\":" + (offset.IsNull ? "0" : offset.ToString()) + "," +
            "\"limit\":" + (limit.IsNull ? "0" : limit.ToString()) + "," +
            "\"acceptPartial\":" + (acceptPartial == null ? "false" : acceptPartial.ToString()) + "," +
            "\"project\":\"" + (project == null ? "default" : project.ToString()) + "\"}";

        string encoded = System.Convert.ToBase64String(System.Text.Encoding.GetEncoding("ISO-8859-1").GetBytes(username.ToString() + ":" + pass.ToString()));
        string JsonHeader = "[{\"Name\":\"Content-Type\",\"Value\":\"application/json;charset=utf-8\"},{\"Name\":\"Authorization\",\"Value\":\"Basic " + encoded + "\"}]";

        var LstResult = new ArrayList();
        ExtendedResult ExtResult = new ExtendedResult();

        string Result = APIConsumer.POSTMethod_Extended(ref ExtResult, URL.ToString(), JsonBody.ToString(), JsonHeader.ToString());
        KylinResponse KylinResponse = JsonConvert.DeserializeObject<KylinResponse>(Result);

        for (int x = 0; x < KylinResponse.results.Count; x++)
        {
            var map = new Dictionary<string, string>();

            for (int y = 0; y < KylinResponse.columnMetas.Length; y++)
            {
                map.Add(KylinResponse.columnMetas[y].name, KylinResponse.results[x][y]);
            }

            LstResult.Add(map);
        }

        Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_RESULT, JsonConvert.SerializeObject(LstResult));
        //Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_RESULT, JsonBody);
        //}
        //catch (Exception ex)
        //{
        //    Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_ERROR, ex.Message.ToString());
        //    ExecutionResult = APIConsumer.FAILED_EXECUTION_RESULT;
        //}

        return ExecutionResult;
    }

    [Microsoft.SqlServer.Server.SqlFunction(
    FillRowMethodName = "FillRow",
    TableDefinition = "CAL_DT Date, LEAF_CATEG_ID int")]
    public static IEnumerable APICaller_GET_Example2(SqlString URL, SqlString JsonBody, SqlString JsonHeader)
    {
        //try
        //{
        var LstResult = new List<Table>();
        ExtendedResult ExtResult = new ExtendedResult();

        string Result = APIConsumer.POSTMethod_Extended(ref ExtResult, URL.ToString(), JsonBody.ToString(), JsonHeader.ToString());
        KylinResponse KylinResponse = JsonConvert.DeserializeObject<KylinResponse>(Result);

        for (int x = 0; x < KylinResponse.results.Count; x++)
        {
            var Table = new Table();

            Table.CAL_DT = KylinResponse.results[x][0];
            Table.LEAF_CATEG_ID = KylinResponse.results[x][1];

            LstResult.Add(Table);
        }

        Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_RESULT, Result);
        //}
        //catch (Exception ex)
        //{
        //    Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_ERROR, ex.Message.ToString());
        //}

        return LstResult;
    }

    public static void FillRow(object obj, out SqlString CAL_DT, out SqlString LEAF_CATEG_ID)
    {
        var property = (Table)obj;
        CAL_DT = property.CAL_DT;
        LEAF_CATEG_ID = property.LEAF_CATEG_ID;
    }

}
