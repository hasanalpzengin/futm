import { Injectable } from '@angular/core';
import { Project } from './models/project.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private baseUrl = `http://localhost:8080/projects`;  // Base URL for the project API

  constructor(private http: HttpClient) {}

  // Create a new project
  createProject(projectRequest: any): Observable<Project> {
    return this.http.post<Project>(`${this.baseUrl}`, projectRequest);
  }

  // Get project by ID
  getProjectById(id: string): Observable<Project> {
    return this.http.get<Project>(`${this.baseUrl}/${id}`);
  }

  // Get all projects
  getAllProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.baseUrl}`);
  }

  // Update an existing project
  updateProject(id: string, name: string, description: string, ownerId: string): Observable<Project> {
    const params = { name, description, ownerId };
    return this.http.put<Project>(`${this.baseUrl}/${id}`, null, { params });
  }

  // Delete a project by ID
  deleteProject(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
