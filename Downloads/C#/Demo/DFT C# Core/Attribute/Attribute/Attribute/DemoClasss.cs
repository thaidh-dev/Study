using System;
using System.Collections.Generic;
using System.Text;

namespace AttributeNamespace
{
    class DemoClasss
    {
        public virtual void Print(string message)
        {
            Console.WriteLine(message);
        }
    }

    class DemoClass2 : DemoClasss
    {
        public override void Print(string message)
        {
            Console.WriteLine("xyz" + message);
        }

        public void thai(string message)
        {
            base.Print(message);
            // gọi lại phương thức của lớp cha khi không bị override
        }

        static void Main(string[] args)
        {
            new DemoClass2().thai("alo alo");
        }
    }
}
