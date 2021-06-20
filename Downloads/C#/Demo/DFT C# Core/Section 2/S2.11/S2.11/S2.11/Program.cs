using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Text;

namespace S2._11
{
    public enum shippingMethod
    {
        e = 1, 
        e2 = 5, 
        e3 = 3
    }

    public class Program 
    {
        static void Main(string[] args) 
        {
            //Console.WriteLine("Hello World!");

            //byte number = 2;
            //int count = 10;
            //float totalPrice = 20.95f;
            //char character = 'A';
            //string firstName = "mosh";
            //bool isWoring = false;
            //var x = "dsds";
            //var y = false;
            //var z = 45;

            //Console.WriteLine(totalPrice);
            //Console.WriteLine(x);
            //Console.WriteLine(y);
            //Console.WriteLine(z);
            //Console.WriteLine(firstName);

            //Console.WriteLine("{0} {1}", byte.MinValue, byte.MaxValue);
            //Console.WriteLine("{0} {1}", float.MinValue, float.MaxValue);
            //Console.WriteLine("{0} {1}", int.MinValue, int.MaxValue);

            //const float Pi = 3.14f; // giống final

            //float f = 9999999999f;
            //int i = (int) f;
            //Console.WriteLine(i);

            //string s = "1";
            //int i2 = Convert.ToInt32(s); // 2 cách như 1
            //int j = int.Parse(s);


            //Console.WriteLine(i2+3);
            //Console.WriteLine(j+2);

            //Console.WriteLine(Calculator.add(4, 8));

            //string name = string.Format("{0} {1}", "alo alo", "t nói Cu bào có nghe thấy không");
            //Console.WriteLine(name);

            //var number2 = new int[3] { 1, 2, 3 };
            //string list = string.Join(",", number2);
            //Console.WriteLine(list);

            //string name2 = "Mosh";
            //char firstChar = name2[0];
            //Console.WriteLine(firstChar);

            //var sm = shippingMethod.e3;
            //Console.WriteLine((int) sm);

            //var method = 2;
            //Console.WriteLine((shippingMethod) method);

            //Console.WriteLine(sm.ToString());

            //var m2 = "e3";
            //var sm2 = (shippingMethod) Enum.Parse(typeof(shippingMethod), m2);
            //Console.WriteLine(sm2);

            //var array = new int[3] { 1, 2, 4 };
            //var array2 = array;
            //array2[0] = 7; // array và array2 cùng chỏ trung vào 1 chỗ trong bộ nhớ, nên đổi 1 cái là cả 2 cái cùng đổi
            //Console.WriteLine("array[0] = {0}, array2[0] = {1}", array[0], array2[0]);

            //var alo = "duong hong thai";
            //foreach (var character23 in alo)
            //{
            //    Console.WriteLine(character23);
            //}

            //while (true)
            //{
            //    Console.Write("alo dong bao co nghe ro không ");
            //    var input = Console.ReadLine();

            //    if (string.IsNullOrWhiteSpace(input))
            //        break;

            //    Console.WriteLine(input);
            //}

            //var lst = new List<int>() { 1, 5, 8 };
            //lst.Add(4);
            //lst.AddRange(new int[3] { 5, 8, 3});
            //foreach (var l in lst)
            //    Console.WriteLine(l);
            //Console.WriteLine(lst.IndexOf(3));

            //var dateTime = new DateTime(2015, 12, 13);
            //var now = DateTime.Now;
            //var today = DateTime.Today;
            //Console.WriteLine("giờ: " + now.Hour);
            //Console.WriteLine(dateTime.Year);

            //var time = new TimeSpan(1, 2, 3);
            //var time2 = new TimeSpan(1, 0, 0);
            //var time3 = TimeSpan.FromHours(1);
            //var start = DateTime.Now;
            //var end = DateTime.Now.AddMinutes(2);
            //var duration = end - start;
            //Console.WriteLine("abc: " + duration);
            //Console.WriteLine("def: " + time.Minutes);
            //Console.WriteLine("ghy: " + time.TotalMinutes);

            //var builder = new StringBuilder();
            //builder.Append('-', 10)
            //    .AppendLine()
            //    .Append("header")
            //    .Append('-', 10)
            //    .Replace('-', '+')
            //    .Remove(0, 10)
            //    .Insert(0, new string('-', 10));
            //Console.WriteLine(builder);
            //Console.WriteLine("Ký tự đầu tiên" + builder[0]);

            //var path = "@c:";
            //File.Copy("", "", true);
            //File.Delete(path);
            //if (File.Exists(path))
            //{

            //}
            //var content = File.ReadAllText(path);
            //var fileInfo = new FileInfo(path);
            //fileInfo.CopyTo("...");
            //fileInfo.Delete();
            //if (fileInfo.Exists) 
            //{

            //}

            //Console.WriteLine("Size of int: {0}", sizeof(long)); // kết quả: 8
            // long là 64 bit - 8 byte

            int? num1 = null;
            //int num3 = null; // sai, không như java đâu bro. phải có ? mới gắn ddc null vào
            int? num2 = 45;

            #if (PI)
                Console.WriteLine("PI is defined");
            #else
                Console.WriteLine("PI is not defined");
            #endif
                Console.WriteLine("buồn ngủ quá");

            Message("In Main function.");
            function1();

        }

        static void function1()
        {
            Message("In Function 1.");
            function2();
        }
        static void function2()
        {
            Message("In Function 2.");
        }

        public static void Message(string msg)
        {
            Console.WriteLine(msg);
        }
    }

    public class Person : thai
    {
        public string firstName;
        private string lastName;

        public void a()
        {
            throw new NotImplementedException();
        }

        public void h()
        {
            throw new NotImplementedException();
        }

        public void introduce()
        {
            Console.WriteLine("My name is " + firstName);
        }

        public void introduce(int x)
        {

        }

        public void t()
        {
            throw new NotImplementedException();
        }
    }

    public interface thai
    {
        void t();
        void h();
        void a();
        private void i()
        {
            Console.WriteLine("thaidh");
        }
    }

    
}
