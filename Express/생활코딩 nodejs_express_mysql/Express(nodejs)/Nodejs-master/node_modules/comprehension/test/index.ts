
import { deepStrictEqual, fail, strictEqual } from 'assert';
import 'mocha';
import $ from '../src';

function throws(func: () => void, expectedError: string) {
	try {
		func();
	} catch (error) {
		strictEqual(error.message, expectedError);
		return;
	}
	fail();
}

it('props has unknwon type', () => {
	try {
		// @ts-ignore
		$('unknown_type');
	} catch (error) {
		strictEqual(error.message, 'unknown props');
		return;
	}
	fail();
});

describe('props is number', () => {
	it('default map', () => deepStrictEqual($(5), [0, 1, 2, 3, 4]));
	it('custom map', () => deepStrictEqual($(5, (i) => 2 * i), [0, 2, 4, 6, 8]));
	it('count is zero', () => deepStrictEqual($(0), []));
	it('raise on negative number', () => throws(() => $(-123), 'count is not unsigned safe integer'));
	it('raise on fractional number', () => throws(() => $(12.3), 'count is not unsigned safe integer'));
});

describe('props is object', () => {
	it('default from, step and map', () => deepStrictEqual($({ to: 4 }), [0, 1, 2, 3, 4]));
	it('raise on no to and count', () => throws(() => $({}), 'to or count is required'));
	it('raise on to and count', () =>
		throws(() => $({ to: 12, count: 15 }), 'only one of to and count must be provided'));
	for (let property of ['from', 'step']) {
		it(`raise on ${property} is not number`, () =>
			// @ts-ignore
			throws(() => $({ to: 123, [property]: 'not_a_number' }), `${property} is not safe integer`));
		it(`raise on ${property} is fractional number`, () =>
			throws(() => $({ [property]: 12.5, to: 15 }), `${property} is not safe integer`));
	}
	describe('to', () => {
		it('from, to, step (include to)', () => deepStrictEqual($({ from: 2, to: 8, step: 3 }), [2, 5, 8]));
		it('from, to, step (not include to)', () => deepStrictEqual($({ from: 2, to: 9, step: 3 }), [2, 5, 8]));
		it('dec (include to)', () => deepStrictEqual($({ from: 3, to: -5, step: -4 }), [3, -1, -5]));
		it('dec (not include to)', () => deepStrictEqual($({ from: 3, to: -6, step: -4 }), [3, -1, -5]));
		// @ts-ignore
		it('raise on to is not number', () => throws(() => $({ to: 'not_a_number' }), 'to is not safe integer'));
		it('raise on to is fractional number', () => throws(() => $({ to: 1.23 }), 'to is not safe integer'));
		it('raise on to < from && step >= 0', () => throws(() => $({ to: -12 }), 'array length is INFINITY'));
		it('raise on to > from && step <= 0', () => throws(() => $({ to: 12, step: -23 }), 'array length is INFINITY'));
	});
	describe('count', () => {
		it('count is zero', () => deepStrictEqual($({ from: 4, count: 0, step: 2 }), []));
		it('inc', () => deepStrictEqual($({ from: 4, count: 3, step: 2 }), [4, 6, 8]));
		it('dec', () => deepStrictEqual($({ from: 4, count: 3, step: -3 }), [4, 1, -2]));
		it('raise on count is not number', () =>
			// @ts-ignore
			throws(() => $({ count: 'not_a_number' }), 'count is not unsigned safe integer'));
		it('raise on count is negative', () => throws(() => $({ count: -123 }), 'count is not unsigned safe integer'));
		it('raise on count is fractional number', () =>
			throws(() => $({ count: 12.3 }), 'count is not unsigned safe integer'));
	});
});
