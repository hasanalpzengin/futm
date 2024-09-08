import { Component, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Project } from '../models/project.model';
import { FutmTableComponent } from "../futm-table/futm-table.component";

const PROJECT_DATA: Project[] = [
  { id: "1", name: 'Project A', description: 'Description of Project A', startDate: '2023-01-01', endDate: '2023-12-31', owner: "", requirements: [], testCases: [], testRuns: []}
  // Add more projects as needed
];

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [FutmTableComponent],
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.scss'
})
export class ProjectsComponent {
  dataSource = new MatTableDataSource<Project>(PROJECT_DATA);
  displayedColumns = ["name", "description", "owner", "startDate", "endDate"]
  columnHeaders = {"name": "Name", "description": "Description", "owner": "Owner", "startDate": "Start", "endDate": "End"}
}