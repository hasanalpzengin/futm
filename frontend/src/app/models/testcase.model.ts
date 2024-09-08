import { Comment } from './comment.model';


export interface TestCase {
    id: string;
    title: string;
    description?: string;
    steps?: string[];
    expectedResult?: string;
    status?: string;
    createdDate?: string;
    updatedDate?: string;
    owner?: string;
    requirement?: string;
    project?: string;
    comments?: Comment[]
}