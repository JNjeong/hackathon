package com.hackaton.hackaton.controller;

import com.hackaton.hackaton.domain.Course;
import com.hackaton.hackaton.domain.CourseTaken;
import com.hackaton.hackaton.domain.Grade;
import com.hackaton.hackaton.domain.User;
import com.hackaton.hackaton.service.CourseService;
import com.hackaton.hackaton.service.CourseTakenService;
import com.hackaton.hackaton.service.GradeService;
import com.hackaton.hackaton.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    UserService userService;
    CourseService courseService;
    CourseTakenService courseTakenService;
    GradeService gradeService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setCourseService(CourseService courseService){
        this.courseService = courseService;
    }

    @Autowired
    public void setCourseTakenService(CourseTakenService courseTakenService){
        this.courseTakenService = courseTakenService;
    }

    @Autowired
    public void setGradeService(GradeService gradeService){
        this.gradeService = gradeService;
    }

    public HomeController(){}

    @RequestMapping("/")
    public String home(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user!=null) {
            if(user.getUser_type().equals("Professor")){
                // 교수가 가르치고 있는 course 정보를 담음
                model.addAttribute("courses", courseService.findByUserId(user));
            }
            else if (user.getUser_type().equals("Student")){
                List<Course> courses = new ArrayList<Course>();
                List<CourseTaken> courseTakens = courseTakenService.findByUserId(user.getUser_id());
                for (CourseTaken courseTaken : courseTakens) {
                    courses.add(courseService.findByCourseId(courseTaken.getCourse_id()));
                }
                // courseTaken 정보로 학생이 듣고 있는 course들을 담아줌
                model.addAttribute("courses", courses);
            }
            return "index";
        }
        else return "redirect:/sign-in";
    }

    @GetMapping("sign-in")
    public void signin(){}

    @PostMapping("sign-in")
    public String signined(String user_id, String user_pw, HttpSession session){
        User user = userService.findById(Long.parseLong(user_id));
        System.out.println(user.getUser_id());
        if (user.getUser_pw().equals(user_pw)) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/sign-in";
    }


    @GetMapping("sign-up")
    public void signup(){}

    @PostMapping("sign-up")
    public String signuped(String user_id, String user_pw, String user_name, String user_email, String user_type, HttpSession session){
        User user = new User();
        user.setUser_id(Long.parseLong((user_id)));
        user.setUser_pw(user_pw);
        user.setUser_name(user_name);
        user.setUser_email(user_email);
        user.setUser_type(user_type);
        userService.register(user);
        session.setAttribute("user", user);
        return "index";
    }

    @GetMapping("tables")
    public String table(@RequestParam("course_id") long course_id, Model model){
        List<User> users = new ArrayList<User>();
        List<CourseTaken> courseTakens = courseTakenService.findByCourseId(course_id);
        for(CourseTaken courseTaken : courseTakens) {
            users.add(userService.findById(courseTaken.getUser_id()));
        }
        model.addAttribute("students", users);
        model.addAttribute("courseName", courseService.findByCourseId(course_id).getCourse_name());
        return "tables";
    }

    @GetMapping("profile")
    public String profile(@RequestParam("user_id") long user_id, Model model){
        model.addAttribute("userName", userService.findById(user_id));
        return "profile";
    }

    @GetMapping("dashboard")
    public String dashboard(@RequestParam("user_id") long user_id, @RequestParam("course_id") long course_id, Model model){
        String msg = "Hello, "+userService.findById(user_id).getUser_name()+". This is what you have achieved so far. Here are achievable goals.\n";

        List<Grade> grades = gradeService.findAllByUserId(user_id, course_id);
        List<Long> Assignments = new ArrayList<Long>();
        long AssCnt = 0;
        long AssSum = 0;
        List<Long> Attendances = new ArrayList<Long>();
        long AttCnt = 0;
        long AttSum = 0;
        List<Long> Quizs = new ArrayList<Long>();
        long QuizCnt = 0;
        long QuizSum = 0;
        List<Long> Exams = new ArrayList<Long>();
        int ExamCnt = 0;
        int ExamSum = 0;
        for(Grade grade : grades){
            String des = grade.getGrade_type();
            long val = grade.getGrade_value();

            if (50 > val) msg = msg.concat(grade.getGrade_description()+" failed. you need more effort on this.\n");
            if (60 <= val && val < 70)  msg = msg.concat(grade.getGrade_description()+" barely passed. You might want more study.\n");
            if (70 <= val && val < 80) msg = msg.concat(grade.getGrade_description()+" needs little more effort. You are on the way!\n");

            if (des.equals("Assignment")) {
                AssCnt++;
                AssSum += val;
                Assignments.add(val);
            }
            else if (des.equals("Attendance")) {
                AttCnt++;
                AttSum += val;
                Attendances.add(val);
            }
            else if (des.equals("quiz")) {
                QuizCnt++;
                QuizSum += val;
                Quizs.add(val);
            }
            else if (des.equals("exam")) {
                ExamCnt++;
                ExamSum += val;
                Exams.add(val);
            }

        }
        msg=msg.concat("Otherwise You are doing GREAT!");

        model.addAttribute("aiMessage", msg);

        model.addAttribute("assignments", Assignments);
        model.addAttribute("assignmentTotal", AssSum);
        model.addAttribute("assignmentCount", AssCnt);
        model.addAttribute("assignmentAverage", AssSum/AssCnt);
        model.addAttribute("attendances", Attendances);
        model.addAttribute("attendanceTotal", AttSum);
        model.addAttribute("attendanceCount", AttCnt);
        model.addAttribute("attendanceAverage", AttSum/AttCnt);
        model.addAttribute("quizs", Quizs);
        model.addAttribute("quizTotal", QuizSum);
        model.addAttribute("quizCount", QuizSum);
        model.addAttribute("quizAverage", QuizSum/QuizCnt);
        model.addAttribute("exams", Exams);
        model.addAttribute("examTotal", ExamSum);
        model.addAttribute("examCnt", ExamCnt);
        model.addAttribute("examAverage", ExamSum/ExamCnt);

        return "dashboard";
    }

}
