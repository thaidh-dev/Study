import 'dart:collection';
import 'dart:convert' as convert;
import 'dart:convert';
import 'dart:io';
import 'package:flutter/foundation.dart';
import 'package:todoey_flutter/models/task.dart';
import 'package:http/http.dart' as http;

class TaskData extends ChangeNotifier {
  List<Task> _tasks = [];

  UnmodifiableListView<Task> get tasks {
    return UnmodifiableListView(_tasks);
  }

  TaskData() {
    getTasksHttpClient();
  }

  // send https request to server
  Future<List<Task>> getTasksHttpClient() async {
    /**
     * 2 dòng code dưới đây đéo có cái tác dụng j vs cái project này hết và cũng đéo chạy đc
     * nhưng vẫn cho vào vì là giải thích cho cái context là gì
     * theo tìm hiểu cho đến hiện tại, thì sẽ có những server yêu cầu client phải gửi cho nó cái client certificate
     *
     * If the server requires a digital certificate for client authentication,
        the server sends a "client certificate request" that includes a list of
        the types of certificates supported and the Distinguished Names of
        acceptable Certification Authorities (CAs).

        The client then compares the certificates in its store against that list
        to see if it has any signed by the CAs that the server listed.
        If it finds one, it will send it,
        usually after prompting the user whether they want to send it.
     */
    // var context = SecurityContext.defaultContext;
    // context.setTrustedCertificates("assets/cert.pem");
    // HttpClient client = new HttpClient(context: context);

    var url = Uri.https("10.0.2.2:8080", "/getTasks", {});
    HttpClient client = new HttpClient();
    /** send https request sử dụng http package
     * chạy cách này sẽ có lỗi CERTIFICATE_VERIFY_FAILED: self signed certificate
     * lý do là:
     *  client send https request lên server java spring boot,
     *  server sẽ send public key cùng vs SSL certificate lại cho client,
     *  client sẽ verify cái certificate đấy is valid,
     *  nhưng cái certificate này là self luv2code-keystore.p12 - signed certificate - certificate tự chế
     * => dẫn đến lỗi này
     *
     * Để sửa thì sử dụng badCertificateCallback
     *  When an secure HTTP request if made, using this HttpClient, and the server returns a server certificate that cannot be authenticated
     *  Sets a callback that will decide whether to accept a secure connection with a server certificate that cannot be authenticated by any of our trusted root certificates.
     */
    client.badCertificateCallback =
        (X509Certificate cert, String host, int port) => true;

    await client.getUrl(url).then((HttpClientRequest request) {
      return request.close();
    }).then((HttpClientResponse response) {
      response.transform(utf8.decoder).listen((contents) {
        var jsonResponse = convert.jsonDecode(contents) as List;
        this._tasks = jsonResponse.map((task) => Task.fromJson(task)).toList();
      });
    });

    notifyListeners();
    return this._tasks;
  }

  /// send https request sử dụng http package
  /// chạy cách này sẽ có lỗi CERTIFICATE_VERIFY_FAILED: self signed certificate
  /// lý do là:
  ///  client send https request lên server java spring boot,
  ///  server sẽ send public key cùng vs SSL certificate lại cho client,
  ///  client sẽ verify cái certificate đấy is valid,
  ///  nhưng cái certificate này là self luv2code-keystore.p12 - signed certificate - certificate tự chế
  /// => dẫn đến lỗi này
  ///
  /// để sửa thì phải dùng cách ở file main.dart
  Future<List<Task>> getTasksHttps() async {
    var url = Uri.https('10.0.2.2:8080', '/getTasks', {});
    var response = await http.get(url);
    if (response.statusCode == 200) {
      var jsonResponse = convert.jsonDecode(response.body) as List;
      _tasks = jsonResponse.map((task) => Task.fromJson(task)).toList();
    }

    notifyListeners();
    return this._tasks;
  }

  /// send http request to server
  Future<List<Task>> getTasksHttp() async {
    var url = Uri.http('10.0.2.2:8080', '/getTasks', {});
    var response = await http.get(url);

    if (response.statusCode == 200) {
      var jsonResponse = convert.jsonDecode(response.body) as List;
      _tasks = jsonResponse.map((task) => Task.fromJson(task)).toList();
    }

    notifyListeners();
    return this._tasks;
  }

  int get taskCount {
    return _tasks.length;
  }

  void addTask(String newTaskTitle) {
    _tasks.add(Task(name: newTaskTitle));
    notifyListeners();
  }

  void updateTask(Task task) {
    task.toggleDone();
    notifyListeners();
  }

  void deleteTask(Task task) {
    _tasks.remove(task);
    notifyListeners();
  }
}
