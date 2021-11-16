using System;
using System.Threading;
using System.Threading.Tasks;

namespace DemoAsync
{
    class Program
    {
        private static Random _rnd;

        static void Main(string[] args)
        {
            //y();

            Console.WriteLine("ali");

            Task.Factory.StartNew(() => Run(SumAsync("A")));
            
            Task.Factory.StartNew(() => Run(SumAsync("B")));



            for (int i = 0; i < 50; i++)
            {
                Thread.Sleep(50);
                Console.WriteLine(i);
            }


            Console.Read();

        }

        private static async void Run(Task<int> xxx)
        {
            var x = await xxx;

            Console.WriteLine("đéo hiểu");

        }

        private static Task<int> SumAsync(string name)
        {
            Console.WriteLine(name + " has got the answer =");
            Thread.Sleep(3000);
            Console.WriteLine("sum");

            return Task.Factory.StartNew(() => 7);
            //return 7;
        }
    }
}
