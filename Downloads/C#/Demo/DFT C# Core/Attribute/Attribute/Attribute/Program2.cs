using System;
using System.Collections.Generic;
using System.Text;

namespace AttributeNamespace
{
    class Program2
    {
        // ví dụ 1
        //static unsafe void Main(string[] args)
        //{
        //    int var = 20;
        //    int* p = &var;

        //    Console.WriteLine("Data is: {0} ", var);
        //    Console.WriteLine("Address is: {0}", (int)p);
        //}

        // ví dụ 2
        //public static void Main()
        //{
        //    unsafe
        //    {
        //        int var = 20;
        //        int* p = &var;

        //        Console.WriteLine("Data is: {0} ", var);
        //        Console.WriteLine("Data is: {0} ", p->ToString());
        //        Console.WriteLine("Address is: {0} ", (int)p);
        //    }
        //}

        // ví dụ 3
        //public unsafe void swap(int* p, int* q)
        //{
        //    int temp = *p;
        //    *p = *q;
        //    *q = temp;
        //}

        //public unsafe static void Main()
        //{
        //    Program2 p = new Program2();
        //    int var1 = 10;
        //    int var2 = 20;
        //    int* x = &var1;
        //    int* y = &var2;

        //    Console.WriteLine("Before Swap: var1:{0}, var2: {1}", var1, var2);
        //    p.swap(x, y);

        //    Console.WriteLine("After Swap: var1:{0}, var2: {1}", var1, var2);
        //    Console.ReadKey();
        //}

        // ví dụ 4
        public unsafe static void Main()
        {
            int[] list = { 10, 100, 200 };
            fixed (int* ptr = list)
                /* let us have array address in pointer */
                for (int i = 0; i < 3; i++)
                {
                    Console.WriteLine("Address of list[{0}]={1}", i, (int)(ptr + i));
                    Console.WriteLine("Value of list[{0}]={1}", i, *(ptr + i));
                }
        }
    }
}
