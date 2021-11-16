import 'package:flutter/material.dart';

class TaskTile extends StatelessWidget {
  late final bool isChecked;
  late final String taskTitle;
  late final Function checkboxCallback;
  late final Function longPressCallback;

  TaskTile({required this.isChecked, required this.taskTitle, required this.checkboxCallback, required this.longPressCallback});

  @override
  Widget build(BuildContext context) {
    return ListTile(
      onLongPress: () {
        this.longPressCallback();
      },
      title: Text(
        this.taskTitle,
        style: TextStyle(
          decoration: isChecked ? TextDecoration.lineThrough : null,
        ),
      ),
      trailing: Checkbox(
        activeColor: Colors.lightBlueAccent,
        value: this.isChecked,
        onChanged: (value) {
          this.checkboxCallback();
        },
      ),
    );
  }
}
