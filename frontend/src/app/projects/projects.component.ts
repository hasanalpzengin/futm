import { Component} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Project } from '../models/project.model';
import { FutmTableComponent } from "../futm-table/futm-table.component";
import { MatDialog } from '@angular/material/dialog';
import { DynamicFormComponent } from '../dynamic-form/dynamic-form.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { ProjectService } from '../project.service';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [FutmTableComponent, MatIconModule, MatButtonModule],
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.scss'
})
export class ProjectsComponent {
  dataSource: MatTableDataSource<Project> = new MatTableDataSource();
  displayedColumns = ["name", "description", "owner", "startDate", "endDate"]
  columnHeaders = {"name": "Name", "description": "Description", "owner": "Owner", "startDate": "Start", "endDate": "End"}

  constructor(public dialog: MatDialog, private projectService: ProjectService){
    projectService.getAllProjects().subscribe(projects => {
      this.dataSource = new MatTableDataSource<Project>(projects)
    })
  }

  openCreateProjectModal(): void {
    const dialogRef = this.dialog.open(DynamicFormComponent, {
      width: '500px',
      data: [
        { type: 'input', field: 'name', placeholder: 'Name', inputType: 'text', validators: [Validators.required] },
        { type: 'textarea', field: 'description', placeholder: 'Description', defaultValue: '' },
        { type: 'datepicker', field: 'startDate', placeholder: 'Start Date', validators: [Validators.required] },
        { type: 'select', field: 'ownerId', placeholder: 'Owner', inputType: 'text', validators: [] },
      ]
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        result.ownerId = result.ownerId == "" ? null : result.ownerId

        this.projectService.createProject(result).subscribe(result=>{
          console.log(result);
          
        })
      }
    });
  }
}