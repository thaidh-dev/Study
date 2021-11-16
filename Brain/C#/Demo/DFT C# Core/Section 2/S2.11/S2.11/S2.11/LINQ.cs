using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace S2._11
{
    class LINQ
    {
        static public void Main(String[] args)
        {
            //IList<string> stringList = new List<string>() {
            //    "C# Tutorials",
            //    "VB.NET Tutorials",
            //    "Learn C++",
            //    "MVC Tutorials" ,
            //    "Java"
            //};

            //var result = from s in stringList
            //             where s.Contains("Tutorials")
            //             select s;

            //foreach (var str in result)
            //{
            //    //Console.WriteLine(str);
            //}

            //// ví dụ 2: where
            //IList<Student> studentList = new List<Student>() {
            //    new Student() { StudentID = 1, StudentName = "John", Age = 13} ,
            //    new Student() { StudentID = 2, StudentName = "Moin",  Age = 21 } ,
            //    new Student() { StudentID = 3, StudentName = "Bill",  Age = 18 } ,
            //    new Student() { StudentID = 4, StudentName = "Ram" , Age = 20} ,
            //    new Student() { StudentID = 5, StudentName = "Ron" , Age = 15 }
            //};

            //// LINQ Query Method to find out teenager students
            //var teenAgerStudent = studentList.Where(s => s.Age > 12 && s.Age < 20);

            //Console.WriteLine(studentList.Count());

            //foreach (Student std in teenAgerStudent)
            //{
            //    Console.WriteLine(std.StudentName);
            //}

            //// ví dụ 3: where
            //var filteredResult = studentList.Where((s, i) => {
            //    if (i % 2 == 0) // if it is even element
            //        return true;

            //    return false;
            //});

            //foreach (var std in filteredResult)
            //    Console.WriteLine(std.StudentName);

            //// ví dụ 4: order by
            //var studentsInAscOrder = studentList.OrderBy(s => s.StudentName);

            //var studentsInDescOrder = studentList.OrderByDescending(s => s.StudentName);

            //Console.WriteLine("Ascending Order:");
            //foreach (var std in studentsInAscOrder)
            //    Console.WriteLine(std.StudentName);

            //Console.WriteLine("Descending Order:");
            //foreach (var std in studentsInDescOrder)
            //    Console.WriteLine(std.StudentName);

            //var multiSortingResult = from s in studentList
            //                         orderby s.StudentName, s.Age
            //                         select s;
            //foreach (var std in multiSortingResult)
            //    Console.WriteLine("Name: {0}, Age {1}", std.StudentName, std.Age);

            //// ví dụ 5: group by
            //var groupedResult = studentList.GroupBy(s => s.Age);

            //foreach (var ageGroup in groupedResult)
            //{
            //    Console.WriteLine("Age Group: {0}", ageGroup.Key);  //Each group has a key 

            //    foreach (Student s in ageGroup)  //Each group has a inner collection  
            //        Console.WriteLine("Student Name: {0}", s.StudentName);
            //}
            // group by giống với tolookup, nhưng mà group by: lazy còn tolookup: eager


            // join
            //IList<string> x = new List<string>() {"One", "Two", "Three", "Four"};

            //IList<string> y = new List<string>() {"One", "Two", "Five", "Six"};

            //var innerJoinResult = x.Join(// outer sequence 
            //              y,  // inner sequence 
            //              a => a,    // outerKeySelector
            //              b => b,  // innerKeySelector
            //              (a, b) => a);

            //foreach (var str in innerJoinResult)
            //{
            //    Console.WriteLine("{0} ", str);
            //}

            //// Join đối tượng 
            //IList<Student> studentList = new List<Student>() {
            //    new Student() { StudentID = 1, StudentName = "John", Age = 18, StandardID = 1 } ,
            //    new Student() { StudentID = 2, StudentName = "Steve",  Age = 21, StandardID = 1 } ,
            //    new Student() { StudentID = 3, StudentName = "Bill",  Age = 18, StandardID = 2 } ,
            //    new Student() { StudentID = 4, StudentName = "Ram" , Age = 20, StandardID = 2 } ,
            //    new Student() { StudentID = 5, StudentName = "Ron" , Age = 21 }
            //};

            //IList<Standard> standardList = new List<Standard>() {
            //    new Standard(){ StandardID = 1, StandardName="Standard 1"},
            //    new Standard(){ StandardID = 2, StandardName="Standard 2"},
            //    new Standard(){ StandardID = 3, StandardName="Standard 3"}
            //};

            //var innerJoinResult2 = studentList.Join(// outer sequence 
            //              standardList,  // inner sequence 
            //              student => student.StandardID,    // outerKeySelector
            //              standard => standard.StandardID,  // innerKeySelector
            //              (student, standard) => new { // result selector
            //                  StudentName = student.StudentName,
            //                  StandardName = standard.StandardName
            //              });

            //foreach (var obj in innerJoinResult2)
            //{
            //    Console.WriteLine("{0} - {1}", obj.StudentName, obj.StandardName);
            //}

            //// join query syntax
            //var innerJoinResult3 = from s in studentList // outer sequence
            //                      join st in standardList //inner sequence 
            //                      on s.StandardID equals st.StandardID // key selector 
            //                      select new
            //                      { // result selector 
            //                          StudentName = s.StudentName,
            //                          StandardName = st.StandardName
            //                      };

            //foreach (var obj in innerJoinResult3)
            //{
            //    Console.WriteLine("{0} - {1}", obj.StudentName, obj.StandardName);
            //}

            //// group join
            //var groupJoin = standardList.GroupJoin(studentList,  //inner sequence
            //                    std => std.StandardID, //outerKeySelector 
            //                    s => s.StandardID,     //innerKeySelector
            //                    (std, studentsGroup) => new // resultSelector 
            //                    {
            //                        Students = studentsGroup,
            //                        StandarFulldName = std.StandardName
            //                    });

            //foreach (var item in groupJoin)
            //{
            //    Console.WriteLine(item.StandarFulldName);

            //    foreach (var stud in item.Students)
            //        Console.WriteLine(stud.StudentName);
            //}

            //// group join in query syntax
            //var groupJoin2 = from std in standardList
            //                join s in studentList
            //                on std.StandardID equals s.StandardID
            //                    into studentGroup
            //                select new
            //                {
            //                    Students = studentGroup,
            //                    StandardName = std.StandardName
            //                };


            //foreach (var item in groupJoin2)
            //{
            //    Console.WriteLine(item.StandardName);

            //    foreach (var stud in item.Students)
            //        Console.WriteLine(stud.StudentName);
            //}

            //// all: tất cả phải đáp ứng yêu cầu
            //IList<Student> studentList = new List<Student>() {
            //    new Student() { StudentID = 1, StudentName = "John", Age = 18 } ,
            //    new Student() { StudentID = 2, StudentName = "Steve",  Age = 15 } ,
            //    new Student() { StudentID = 3, StudentName = "Bill",  Age = 25 } ,
            //    new Student() { StudentID = 4, StudentName = "Ram" , Age = 20 } ,
            //    new Student() { StudentID = 5, StudentName = "Ron" , Age = 19 }
            //};

            //// checks whether all the students are teenagers    
            //bool areAllStudentsTeenAger = studentList.All(s => s.Age > 12 && s.Age < 20);

            //Console.WriteLine(areAllStudentsTeenAger);

            //// any: chỉ cần có 1 cái đáp ứng yêu cầu là về true
            //// checks whether any of the students is teenager   
            //bool isAnyStudentTeenAger = studentList.Any(s => s.Age > 12 && s.Age < 20);
            //Console.WriteLine(isAnyStudentTeenAger);

            //// contain
            //IList<int> intList = new List<int>() { 1, 2, 3, 4, 5 };
            //bool result = intList.Contains(10);  // returns false

            //// so sánh đối tượng: https://www.tutorialsteacher.com/codeeditor?cid=cs-EVTaLz

            //// aggregate
            //IList<String> strList = new List<String>() { "One", "Two", "Three", "Four", "Five" };
            //var commaSeperatedString = strList.Aggregate((s1, s2) => s1 + ", " + s2);
            //Console.WriteLine(commaSeperatedString);

            //// Aggregate with Seed Value C#
            //IList<Student> studentList = new List<Student> () {
            //    new Student() { StudentID = 1, StudentName = "John", Age = 13 } ,
            //    new Student() { StudentID = 2, StudentName = "Moin", Age = 21 } ,
            //    new Student() { StudentID = 3, StudentName = "Bill", Age = 18 } ,
            //    new Student() { StudentID = 4, StudentName = "Ram", Age = 20 } ,
            //    new Student() { StudentID = 5, StudentName = "Ron", Age = 15 }
            //};
            //string commaSeparatedStudentNames = 
            //    studentList.Aggregate<Student, string>("Tên học sinh: ", (str, s) => str += s.StudentName + ",");
            //Console.WriteLine(commaSeparatedStudentNames);

            //int SumOfStudentsAge = studentList.Aggregate<Student, int>(0, (age, s) => age += s.Age);
            //Console.WriteLine(SumOfStudentsAge);

            //// xóa dấu phẩy ở cuối
            //string commaSeparatedStudentNames2 = 
            //    studentList.Aggregate<Student, string, string>(
            //        String.Empty, 
            //        (str, s) => str += s.StudentName + ",", 
            //        str => str.Substring(0, str.Length - 1));
            //Console.WriteLine(commaSeparatedStudentNames2);

            //// average
            //IList<Student> studentListAverage = new List<Student> () {
            //    new Student() { StudentID = 1, StudentName = "John", Age = 13 } ,
            //    new Student() { StudentID = 2, StudentName = "Moin", Age = 21 } ,
            //    new Student() { StudentID = 3, StudentName = "Bill", Age = 18 } ,
            //    new Student() { StudentID = 4, StudentName = "Ram", Age = 20 } ,
            //    new Student() { StudentID = 5, StudentName = "Ron", Age = 15 }
            // };
            //var avgAge = studentListAverage.Average(s => s.Age);
            //Console.WriteLine("Average Age of Student: {0}", avgAge);

            //// max
            //IList<StudentMax> studentList = new List<StudentMax>() {
            //    new StudentMax() { StudentID = 1, StudentName = "John", Age = 13 } ,
            //    new StudentMax() { StudentID = 2, StudentName = "Moin",  Age = 21 } ,
            //    new StudentMax() { StudentID = 3, StudentName = "Bill",  Age = 18 } ,
            //    new StudentMax() { StudentID = 4, StudentName = "Ram" , Age = 20 } ,
            //    new StudentMax() { StudentID = 5, StudentName = "Steve" , Age = 15 }
            //};
            //var studentWithLongName = studentList.Max();
            //Console.WriteLine("Student ID: {0}, Student Name: {1}", studentWithLongName.StudentID, studentWithLongName.StudentName);

            //// single: chỉ 1
            //IList<int> oneElementList = new List<int>() { 7 };
            //IList<int> intList = new List<int>() { 100, 10, 21, 30, 45, 50, 87, 60 };
            //IList<string> strList = new List<string>() { null, "Two", "Three", "Four", "Five" };
            //IList<string> emptyList = new List<string>();

            //Console.WriteLine("The only element in oneElementList: {0}", oneElementList.Single());
            //Console.WriteLine("The only element in oneElementList: {0}", oneElementList.SingleOrDefault());
            //Console.WriteLine("Element in emptyList: {0}", emptyList.SingleOrDefault());
            //Console.WriteLine("The only element which is less than 10 in intList: {0}", intList.Single(i => i <= 10));

            //// sequence equal
            //IList<string> strList1 = new List<string>() { "One", "Two", "Three", "Four", "Three" };
            //IList<string> strList2 = new List<string>() { "One", "Two", "Three", "Four", "Three" };
            //bool isEqual = strList1.SequenceEqual(strList2);
            //Console.WriteLine(isEqual);

            //// sequence equal: so sánh đối tượng
            //Student std = new Student() { 
            //    StudentID = 1, 
            //    StudentName = "Bill" 
            //};
            //IList<Student> studentList1 = new List<Student>() { std };
            //IList<Student> studentList2 = new List<Student>() { std };
            //bool isEqual2 = studentList1.SequenceEqual(studentList2); // returns true

            //Student std1 = new Student() { 
            //    StudentID = 1, 
            //    StudentName = "Bill" 
            //};
            //Student std2 = new Student() { 
            //    StudentID = 1, 
            //    StudentName = "Bill" 
            //};
            //IList<Student> studentList3 = new List<Student>() { std1 };
            //IList<Student> studentList4 = new List<Student>() { std2 };
            //bool isEqual3 = studentList3.SequenceEqual(studentList4);// returns false

            //// concat: nối cả 2 mảng với nhau

            //// default empty
            //IList<string> emptyList = new List<string>();

            //var newList1 = emptyList.DefaultIfEmpty();
            //var newList2 = emptyList.DefaultIfEmpty("None");

            //Console.WriteLine("Count: {0}", newList1.Count());
            //Console.WriteLine("Value: {0}", newList1.ElementAt(0));

            //Console.WriteLine("Count: {0}", newList2.Count());
            //Console.WriteLine("Value: {0}", newList2.ElementAt(0));

            //// empty
            //var emptyCollection1 = Enumerable.Empty<string>();
            //var emptyCollection2 = Enumerable.Empty<Student>();

            //Console.WriteLine("Count: {0} ", emptyCollection1.Count());
            //Console.WriteLine("Type: {0} ", emptyCollection1.GetType().Name);

            //Console.WriteLine("Count: {0} ", emptyCollection2.Count());
            //Console.WriteLine("Type: {0} ", emptyCollection2.GetType().Name);

            //// range
            //var intCollection = Enumerable.Range(10, 10);
            //Console.WriteLine("Total Count: {0} ", intCollection.Count());

            //for (int i = 0; i < intCollection.Count(); i++)
            //    Console.WriteLine("Value at index {0} : {1}", i, intCollection.ElementAt(i));

            //// repeat
            //var intCollectionRepeat = Enumerable.Repeat<int>(7, 5);
            //Console.WriteLine("Total Count: {0} ", intCollectionRepeat.Count());

            //for (int i = 0; i < intCollectionRepeat.Count(); i++)
            //    Console.WriteLine("Value at index {0} : {1}", i, intCollectionRepeat.ElementAt(i));

            //// distinct
            //IList<string> strList = new List<string>() { "One", "Two", "Three", "Two", "Three" };

            //IList<int> intList = new List<int>() { 1, 2, 3, 2, 4, 4, 3, 5 };

            //var distinctList1 = strList.Distinct();

            //foreach (var str in distinctList1)
            //    Console.WriteLine(str);

            //var distinctList2 = intList.Distinct();

            //foreach (var i in distinctList2)
            //    Console.WriteLine(i);

            // union
            IList<string> strList1 = new List<string>() { "One", "Two", "three", "Four" };
            IList<string> strList2 = new List<string>() { "Two", "THREE", "Four", "Five" };

            var result = strList1.Union(strList2);

            foreach (string str in result)
                Console.WriteLine(str);

        }
    }

    public class Student
    {
        public int StudentID { get; set; }
        public string StudentName { get; set; }
        public int Age { get; set; }
        public int StandardID { get; set; }
    }

    public class Standard
    {
        public int StandardID { get; set; }
        public string StandardName { get; set; }
    }

    public class StudentMax : IComparable<StudentMax>
    {
        public int StudentID { get; set; }
        public string StudentName { get; set; }
        public int Age { get; set; }
        public int StandardID { get; set; }

        public int CompareTo(StudentMax other)
        {
            if (this.StudentName.Length >= other.StudentName.Length)
                return 1;

            return 0;
        }
    }

    public class StudentSoSanh
    {
        public int StudentID { get; set; }
        public string StudentName { get; set; }
    }

    class StudentComparer : IEqualityComparer<StudentSoSanh>
    {
        public bool Equals(StudentSoSanh x, StudentSoSanh y)
        {
            if (x.StudentID == y.StudentID && x.StudentName.ToLower() == y.StudentName.ToLower())
                return true;

            return false;
        }

        public int GetHashCode(StudentSoSanh obj)
        {
            return obj.GetHashCode();
        }
    }
}
