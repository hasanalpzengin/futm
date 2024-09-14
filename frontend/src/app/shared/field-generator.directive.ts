import { Directive, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Directive({
  selector: '[appFieldGenerator]',
  standalone: true
})
export class FieldGeneratorDirective implements OnInit {
  @Input() field: any;   // Field configuration
  @Input() form!: FormGroup;  // The form group where the field is added

  ngOnInit() {
    this.generateField();
  }

  generateField() {
    if (!this.form) {
      console.error('Form is not initialized');
      return;
    }

    if (!this.field) {
      console.error('Field configuration is not provided');
      return;
    }

    console.log('Generating field:', this.field);

    let control: FormControl;

    const defaultValue = this.field.defaultValue || '';
    const validators = this.field.validators || [];

    switch (this.field.type) {
      case 'input':
        control = new FormControl(defaultValue, validators);
        break;
      case 'textarea':
        control = new FormControl(defaultValue, validators);
        break;
      case 'select':
        control = new FormControl(defaultValue, validators);
        break;
      case 'checkbox':
        control = new FormControl(false, validators);
        break;
      default:
        console.error(`Unknown field type: ${this.field.type}`);
        return;
    }

    // Add the control to the form with the specified field name
    this.form.addControl(this.field.field, control);
    console.log(`Field ${this.field.field} added to the form`);
  }
}
