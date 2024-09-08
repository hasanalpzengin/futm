import { Requirement } from "./requirement.model";
import { TestCase } from "./testcase.model";
import { TestRun } from "./testrun.model";

export interface Project {
    id: string;
    name: string;
    description?: string;
    startDate?: string;
    endDate?: string;
    owner?: string;
    requirements?: Requirement[];
    testCases?: TestCase[];
    testRuns?: TestRun[];
}