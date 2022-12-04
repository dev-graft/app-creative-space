export type IException = {
    code: number | undefined,
    message: string | undefined
    callback: () => void
}

export interface IOptional<T> {
    isPresent(): boolean
    get(): T
}