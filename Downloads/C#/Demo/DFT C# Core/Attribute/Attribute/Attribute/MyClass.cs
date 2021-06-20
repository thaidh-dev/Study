using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;

namespace AttributeNamespace
{
    class MyClass
    {
        [Obsolete("don't use oldmethod, use newmethod instead", true)]
        static void OldMethod()
        {
            Console.WriteLine("It is the old method");
        }

        static Thread tx = new Thread(NewMethod);

        static void NewMethod()
        {
            Console.WriteLine("It is the new method");
        }

        public static void Main()
        {
            tx.Start();
            tx.Abort(); //destroy thread
            //OldMethod();
        }
    }
}
