import { Component, OnInit } from '@angular/core';
import {CourseModel} from "../models/course.model";
import {CourseService} from "../services/course.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  courses$: Array<CourseModel> = [];

  constructor(private courseService: CourseService) {
    this.courseService.getAllCourses().subscribe(course => {
        this.courses$ = course;
    });
  }

  ngOnInit(): void {
  }

}
