export interface TestRun {
    id: string;
    name: string;
    description?: string;
    startDate?: string;
    endDate?: string;
    owner?: string;
    project?: string;
    testResults?: string[]
}