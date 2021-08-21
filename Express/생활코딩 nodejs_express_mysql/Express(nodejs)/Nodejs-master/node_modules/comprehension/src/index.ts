
function isUnsignedSafeInteger(a: number): boolean {
	return Number.isSafeInteger(a) && a >= 0;
}

export default function $<T>(props: number | {
	from?: number,
	to?: number,
	count?: number,
	step?: number,
}, map?: (index: number) => T): Array<T> {
	// @ts-ignore
	if (!map) map = (i) => i;
	if (typeof props === 'number') {
		if (!isUnsignedSafeInteger(props)) throw new Error('count is not unsigned safe integer');
		return new Array(props).fill(null).map((_, index) => map!(index));
	}
	if (typeof props === 'object') {
		const { from = 0, to, count, step = 1 } = props;
		if ([to, count].every((a) => a === undefined)) throw new Error('to or count is required');
		if ([to, count].every((a) => a !== undefined)) {
			throw new Error('only one of to and count must be provided');
		}
		if (!Number.isSafeInteger(from)) throw new Error('from is not safe integer');
		if (!Number.isSafeInteger(step)) throw new Error('step is not safe integer');
		if (to !== undefined) {
			if (!Number.isSafeInteger(to)) throw new Error('to is not safe integer');
			if (
				(to < from && step >= 0) ||
				(to > from && step <= 0)
			) throw new Error('array length is INFINITY');
			return $(Math.floor(Math.abs(to - from) / Math.abs(step) + 1), (i) => from + step * i).map((i) => map!(i));
		}
		if (!isUnsignedSafeInteger(count as number)) throw new Error('count is not unsigned safe integer');
		return $(count as number, (i) => from + step * i).map((i) => map!(i));
	}
	throw new Error('unknown props');
}
