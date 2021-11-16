using System;

namespace AttributeNamespace
{
    [AttributeUsage(AttributeTargets.Class | AttributeTargets.Struct | AttributeTargets.Method)]
    class ProgramAttribute : Attribute
    {
        private string author;
        public string Url { get; set; }

        public ProgramAttribute(string author)
        {
            this.author = author;
        }

        public override string ToString()
        {
            return String.Format("Author: {0}\nLocation: {1}", author, Url);
        }
    }
    
    [ProgramAttribute("YinYang", Url = "https://yinyangit.wordpress.com")]
    class DemoClass
    {
        public void Print(string message)
        {
            Console.WriteLine(message);
        }

        static void Main(string[] args)
        {
            Attribute[] attributes = Attribute.GetCustomAttributes(typeof(DemoClass));
            foreach (var attr in attributes)
            {
                Console.WriteLine(attr);
            }
        }
    }
}
