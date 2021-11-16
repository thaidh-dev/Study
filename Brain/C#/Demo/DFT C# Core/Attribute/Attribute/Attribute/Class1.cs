#define CONDITION1

using System;
using System.Collections.Generic;
using System.Text;
using System.Diagnostics;

namespace AttributeNamespace
{
    class Class1
    {
        static void Main(string[] args)
        {
            PrintA("Test A");
            new Class1().PrintB("Test Program B");
            new NewProgram().PrintB("Test New Program B");
        }

        [Conditional("CONDITION1")]
        public static void PrintA(string message)
        {
            Console.WriteLine(message);
        }

        [Conditional("CONDITION1"), Conditional("CONDITION2")]
        public virtual void PrintB(string message)
        {
            Console.WriteLine(message);
        }

        class NewProgram : Class1
        {
            public override void PrintB(string message)
            {
                base.PrintB(message);
            }
        }
    }
}
