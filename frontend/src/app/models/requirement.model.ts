import { Comment } from './comment.model';

export interface Requirement {
    id: string;
    title: string;
    description?: string;
    status?: string;
    updatedDate?: string;
    owner?: string;
    comments?: Comment[]
}