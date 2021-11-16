using API_Consumer;
using Newtonsoft.Json;
using SQLAPI_Consumer;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Data.SqlTypes;

/// <summary>
/// Generic Post Api Consumer thought CLR Proc
/// </summary>
public partial class StoredProcedures
{
    /// <summary>
    /// It's a generic procedure used to consume Api throught POST method.
    /// It could either returns a result or not.
    /// </summary>
    /// <param name="URL">Consumer POST Method of Api</param>
    /// <param name="JsonBody">Json to be sent as body</param>
    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_POST(SqlString URL, SqlString JsonBody)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;
        try
        {
            string Result = APIConsumer.POSTMethod(URL.ToString(), JsonBody.ToString());

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
    /// It's a generic procedure used to consume Api throught POST method.
    /// It could either returns a result or not.
    /// </summary>
    /// <param name="URL">Consumer POST Method of Api</param>
    /// <param name="Authorization">Authorization Header</param>
    /// <param name="JsonBody">Json to be sent as body</param>
    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_POST_Auth(SqlString URL, SqlString Authorization, SqlString JsonBody)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;
        try
        {
            string Result = APIConsumer.POSTMethod(URL.ToString(), JsonBody.ToString(), Authorization.ToString());

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
    /// It's a generic procedure used to consume Api throught POST method.
    /// It could either returns a result or not.
    /// </summary>
    /// <param name="URL">Consumer POST Method of Api</param>
    /// <param name="Headers">Authorization Header</param>
    /// <param name="JsonBody">Json to be sent as body</param>
    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_POST_JsonBody_Headers(SqlString URL, SqlString Headers, SqlString JsonBody)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;

        try
        {
            string Result = APIConsumer.POSTMethod_Header(URL.ToString(), JsonBody.ToString(), Headers.ToString());

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
    /// It's a generic procedure used to consume Api throught POST method.
    /// It could either returns a result or not.
    /// </summary>
    /// <param name="URL">Consumer POST Method of Api</param>
    /// <param name="Headers">Authorization Header</param>
    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_POST_Headers(SqlString URL, SqlString Headers)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;
        try
        {
            string Result = APIConsumer.POSTMethod_Header(URL.ToString(), Headers.ToString());

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
    /// It's a generic procedure used to consume API throught POST method.
    /// It could either returns a result or not.
    /// Content Type must be suplied as headers
    /// </summary>
    /// <param name="URL">Consumer POST Method of Api</param>
    /// <param name="Headers">Authorization Header</param>
    /// <param name="JsonBody">Json to be sent as body</param>
    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_POST_Extended(SqlString URL, SqlString Headers, SqlString JsonBody)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;
        try
        {
            API_Consumer.ExtendedResult ExtResult = new API_Consumer.ExtendedResult();

            string Result = APIConsumer.POSTMethod_Extended(ref ExtResult, URL.ToString(), JsonBody.ToString(), Headers.ToString());

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
    /// It's a generic procedure used to consume Api throught POST method.
    /// It could either returns a result or not.
    /// Content type application/x-www-form-urlencoded
    /// </summary>
    /// <param name="URL">Consumer POST Method of Api</param>
    /// <param name="Headers">Authorization Header</param>
    /// <param name="JsonBody">Json to be sent as body</param>
    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APICaller_POST_Encoded(SqlString URL, SqlString Headers, SqlString JsonBody)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;
        try
        {
            string Result = APIConsumer.POSTMethod_urlencoded(URL.ToString(), JsonBody.ToString(), Headers.ToString());

            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_RESULT, Result);

        }
        catch (Exception ex)
        {
            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_ERROR, ex.Message.ToString());
            ExecutionResult = APIConsumer.FAILED_EXECUTION_RESULT;
        }

        return ExecutionResult;
    }

    [Microsoft.SqlServer.Server.SqlProcedure]
    public static SqlInt32 APIKylinQueryMain(SqlString URL, SqlString UserName, SqlString Password, SqlString Sql, SqlInt32 Offset, SqlInt32 Limit, SqlBoolean AcceptPartial, SqlString Project)
    {
        SqlInt32 ExecutionResult = APIConsumer.DEFAULT_EXECUTION_RESULT;
        try
        {
            bool isContinue = true;
            if (URL.IsNull
                || (!URL.IsNull & String.IsNullOrEmpty(URL.ToString()))
                || UserName.IsNull
                || (!UserName.IsNull & String.IsNullOrEmpty(UserName.ToString()))
                || Password.IsNull
                || (!Password.IsNull & String.IsNullOrEmpty(Password.ToString()))
                || Sql.IsNull
                || (!Sql.IsNull & String.IsNullOrEmpty(Sql.ToString()))
            )
            {
                isContinue = false;
            }

            if (isContinue)
            {
                string Headers = "";
                string JsonBody = "";

                // x? lý Jsonbody
                var bodyDiction = new Dictionary<string, string>();

                bodyDiction.Add("sql", Sql.ToString());

                if (!Offset.IsNull && Offset >= 0)
                {
                    bodyDiction.Add("offset", Offset.ToString());
                }

                if (!Limit.IsNull && Limit >= 0)
                {
                    bodyDiction.Add("limit", Limit.ToString());
                }
                else
                {
                    bodyDiction.Add("limit", "100");
                }

                if (!AcceptPartial.IsNull)
                {
                    if (AcceptPartial.IsFalse)
                        bodyDiction.Add("acceptPartial", "false");
                    else if (AcceptPartial.IsTrue)
                        bodyDiction.Add("acceptPartial", "true");
                    else
                        bodyDiction.Add("acceptPartial", "false");
                }
                else
                {
                    bodyDiction.Add("acceptPartial", "false");
                }

                if (!Project.IsNull)
                {
                    bodyDiction.Add("project", Project.ToString());
                }
                else
                {
                    bodyDiction.Add("project", "DEFAULT");
                }

                JsonBody = JsonConvert.SerializeObject(bodyDiction);

                // x? lý header v?i basic auth
                string encoded = Convert.ToBase64String(System.Text.Encoding.GetEncoding("ISO-8859-1").GetBytes(UserName.ToString() + ":" + Password.ToString()));
                var headerDiction = new List<Dictionary<string, string>>();

                var jsonType = new Dictionary<string, string>();
                jsonType.Add("Name", "Content-Type");
                jsonType.Add("Value", "application/json; charset=utf-8");
                headerDiction.Add(jsonType);

                var basicAuth = new Dictionary<string, string>();
                basicAuth.Add("Name", "Authorization");
                basicAuth.Add("Value", "Basic " + encoded);
                headerDiction.Add(basicAuth);

                Headers = JsonConvert.SerializeObject(headerDiction);

                try
                {
                    API_Consumer.ExtendedResult ExtResult = new API_Consumer.ExtendedResult();

                    string Result = APIConsumer.POSTMethod_Extended(ref ExtResult, URL.ToString(), JsonBody, Headers);

                    DataShape res = JsonConvert.DeserializeObject<DataShape>(Result);
                    var dataRows = new ArrayList();

                    for (int x = 0; x < res.results.Count; x++)
                    {
                        var dic = new Dictionary<string, string>();
                        for (int y = 0; y < res.columnMetas.Count; y++)
                        {
                            dic.Add(res.columnMetas[y].name, res.results[x][y]);
                        }
                        dataRows.Add(dic);
                    }

                    Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_RESULT, JsonConvert.SerializeObject(dataRows));
                }
                catch (Exception ex)
                {
                    Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_ERROR, ex.Message.ToString());
                    ExecutionResult = APIConsumer.FAILED_EXECUTION_RESULT;
                }
                // dùng ?? test thông s? input và query
                //Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_RESULT, new StringBuilder($"UserName: {UserName}, Password: {Password}, Limit: {Limit}, Offset: {Offset}, AcceptPartial: {AcceptPartial}, Project: {Project}, Header: {Headers}, Body: {JsonBody}").ToString());
            }
            else
            {
                Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_ERROR, "Username, Password, Url, Sql are required!");
                ExecutionResult = APIConsumer.FAILED_EXECUTION_RESULT;
            }
        }
        catch (Exception ex)
        {
            Helper.SendResultValue(APIConsumer.DEFAULT_COLUMN_ERROR, ex.Message.ToString());
            ExecutionResult = APIConsumer.FAILED_EXECUTION_RESULT;
        }

        return ExecutionResult;
    }

}
