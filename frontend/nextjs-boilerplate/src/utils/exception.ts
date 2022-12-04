import {IException} from "@/types";

export class Exception implements IException {
    code: number | undefined;
    message: string | undefined;
    callback: () => void;

    constructor(code: number | undefined = undefined, message: string | undefined = undefined, callback: () => void = () => { /* document why this arrow function is empty */
    }) {
        this.code = code;
        this.message = message;
        this.callback = callback
    }
}