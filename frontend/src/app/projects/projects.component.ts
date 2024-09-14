import { Component} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Project } from '../models/project.model';
import { FutmTableComponent } from "../futm-table/futm-table.component";
import { MatDialog } from '@angular/material/dialog';
import { DynamicFormComponent } from '../dynamic-form/dynamic-form.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

const PROJECT_DATA: Project[] = [
  { id: "1", name: 'Project A', description: 'Description of Project A', startDate: '2023-01-01', endDate: '2023-12-31', owner: "", requirements: [], testCases: [], testRuns: []}
  // Add more projects as needed
];

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [FutmTableComponent, MatIconModule, MatButtonModule],
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.scss'
})
export class ProjectsComponent {
  dataSource = new MatTableDataSource<Project>(PROJECT_DATA);
  displayedColumns = ["name", "description", "owner", "startDate", "endDate"]
  columnHeaders = {"name": "Name", "description": "Description", "owner": "Owner", "startDate": "Start", "endDate": "End"}

  constructor(public dialog: MatDialog){}

  openCreateProjectModal(): void {
    const dialogRef = this.dialog.open(DynamicFormComponent, {
      width: '500px',
      data: [
        { type: 'input', field: 'username', placeholder: 'Enter username', inputType: 'text', defaultValue: '', validators: [] },
        { type: 'textarea', field: 'bio', placeholder: 'Enter bio', defaultValue: '' },
        { type: 'select', field: 'country', options: [{value: 'US', label: 'United States'}, {value: 'CA', label: 'Canada'}] },
        { type: 'checkbox', field: 'acceptTerms', defaultValue: false, placeholder: "Checkbox" }
      ]
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        console.log('Modal result:', result);
      }
    });
  }
}