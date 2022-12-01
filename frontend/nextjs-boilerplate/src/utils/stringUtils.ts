export function hasText(text: string | undefined): boolean {
    if (text === undefined) return false;
    return 0 !== text.length;
}