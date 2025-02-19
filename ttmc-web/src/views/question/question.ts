import { Theme } from "../theme/theme";

export interface Question {
    id: number;
    version: number;
    nomQuestion: string;
    reponse: string;
    difficulte: number;
    theme: Theme;
}