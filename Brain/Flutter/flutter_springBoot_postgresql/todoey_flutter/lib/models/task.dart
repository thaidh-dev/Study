class Task {
  late int id;
  late String name;
  late bool isDone;

  Task({required this.name, this.isDone = false});

  Task.fromTask(int id, String name) {
    this.id = id;
    this.name = name;
    this.isDone = false;
  }

  void toggleDone() {
    this.isDone = !this.isDone;
  }

  factory Task.fromJson(Map<String, dynamic> json) {
    return Task.fromTask(json['id'], json['name']);
  }
}
