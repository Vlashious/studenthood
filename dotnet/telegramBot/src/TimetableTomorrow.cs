using System.Collections.Generic;

public class StudentGroup
{
    public string name { get; set; }
    public int facultyId { get; set; }
    public object facultyName { get; set; }
    public int specialityDepartmentEducationFormId { get; set; }
    public object specialityName { get; set; }
    public int course { get; set; }
    public int id { get; set; }
    public string calendarId { get; set; }
}

public class Schedule2
{
    public List<int> weekNumber { get; set; }
    public List<string> studentGroup { get; set; }
    public List<string> studentGroupInformation { get; set; }
    public int numSubgroup { get; set; }
    public List<object> auditory { get; set; }
    public string lessonTime { get; set; }
    public string startLessonTime { get; set; }
    public string endLessonTime { get; set; }
    public object gradebookLesson { get; set; }
    public string subject { get; set; }
    public string note { get; set; }
    public string lessonType { get; set; }
    public List<object> employee { get; set; }
    public object studentGroupModelList { get; set; }
    public bool zaoch { get; set; }
    public object gradebookLessonlist { get; set; }
}

public class Schedule
{
    public string weekDay { get; set; }
    public List<Schedule2> schedule { get; set; }
}

public class Employee
{
    public string firstName { get; set; }
    public string lastName { get; set; }
    public string middleName { get; set; }
    public string rank { get; set; }
    public string photoLink { get; set; }
    public string calendarId { get; set; }
    public List<string> academicDepartment { get; set; }
    public int id { get; set; }
    public object email { get; set; }
    public string fio { get; set; }
}

public class TodaySchedule
{
    public List<int> weekNumber { get; set; }
    public List<string> studentGroup { get; set; }
    public List<string> studentGroupInformation { get; set; }
    public int numSubgroup { get; set; }
    public List<string> auditory { get; set; }
    public string lessonTime { get; set; }
    public string startLessonTime { get; set; }
    public string endLessonTime { get; set; }
    public object gradebookLesson { get; set; }
    public string subject { get; set; }
    public object note { get; set; }
    public string lessonType { get; set; }
    public List<Employee> employee { get; set; }
    public object studentGroupModelList { get; set; }
    public bool zaoch { get; set; }
    public object gradebookLessonlist { get; set; }
}

public class TomorrowSchedule
{
    public List<int> weekNumber { get; set; }
    public List<string> studentGroup { get; set; }
    public List<string> studentGroupInformation { get; set; }
    public int numSubgroup { get; set; }
    public List<object> auditory { get; set; }
    public string lessonTime { get; set; }
    public string startLessonTime { get; set; }
    public string endLessonTime { get; set; }
    public object gradebookLesson { get; set; }
    public string subject { get; set; }
    public object note { get; set; }
    public string lessonType { get; set; }
    public List<object> employee { get; set; }
    public object studentGroupModelList { get; set; }
    public bool zaoch { get; set; }
    public object gradebookLessonlist { get; set; }
}

public class JSONTimetable
{
    public object employee { get; set; }
    public StudentGroup studentGroup { get; set; }
    public List<Schedule> schedules { get; set; }
    public List<object> examSchedules { get; set; }
    public string todayDate { get; set; }
    public List<TodaySchedule> todaySchedules { get; set; }
    public string tomorrowDate { get; set; }
    public List<TomorrowSchedule> tomorrowSchedules { get; set; }
    public int currentWeekNumber { get; set; }
    public string dateStart { get; set; }
    public string dateEnd { get; set; }
    public object sessionStart { get; set; }
    public object sessionEnd { get; set; }
}

public class EDITORCONFIG
{
    public string text { get; set; }
    public string mode { get; set; }
}

public class RootObject
{
    public JSONTimetable JSON { get; set; }
}