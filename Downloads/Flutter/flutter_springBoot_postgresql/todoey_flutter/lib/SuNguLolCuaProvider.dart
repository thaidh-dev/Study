import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<Data>(
      create: (context) => Data(),
      child: MaterialApp(
        home: Scaffold(
          appBar: AppBar(
            title: MyText(),
          ),
          body: Level1(),
        ),
      ),
    );
  }
}

class Level1 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Level2(),
    );
  }
}

class Level2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        MyTextField(),
        Level3(),
      ],
    );
  }
}

class Level3 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        // sự ngu lol của listen:
        // + trong cùng 1 level, chỉ cần 1 cái Provider.of<Data> có listen: true -> tất cả đều sẽ đc set true
        Text(Provider.of<Data>(context, listen: false).data),
        Text(Provider.of<Data>(context, listen: true).abc),
      ],
    );
  }
}

class MyText extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // + khác level thì đéo sao
    return Text(Provider.of<Data>(context, listen: false).data);
  }
}

class MyTextField extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return TextField(
      onChanged: (newText) {
        Provider.of<Data>(context, listen: false).changeString(newText);
      },
    );
  }
}

class Data extends ChangeNotifier {
  String data = "data";
  String abc = "abc";

  void changeString(String newString) {
    data = newString;
    notifyListeners();
  }
}
