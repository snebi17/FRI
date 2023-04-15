// Return vectors as arrays, e.g.:
// return [ 1, 2, 3, 4 ];

export function negate(v) {
    // TODO implement
    for (let i = 0; i < v.length; i++) {
        v[i] *= -1;
    }
    return v;
}

export function add(v, w) {
    // TODO implement
    let res;
    let size = v.length;
    for (let i = 0; i < size; i++) {
        res[i] = v[i] + w[i];
    }
    return res;
}

export function subtract(v, w) {
    // TODO implement
    let res;
    let size = v.length;
    for (let i = 0; i < size; i++) {
        res[i] = v[i] + negate(w)[i];
    }
    return res;
}

export function multiply(v, w) {
    // TODO implement
    let res;
    let size = v.length;
    for (let i = 0; i < size; i++) {
        res[i] = v[i] * w[i];
    }
    return res;
}

export function divide(v, w) {
    // TODO implement
    let res;
    let size = v.length;
    for (let i = 0; i < size; i++) {
        res[i] = v[i] / w[i];
    }
    return res;
}

export function dot(v, w) {
    // TODO implement
    let phi;
    let mag_v = ;
    let mag_w = ;
}

export function cross(v, w) {
    // TODO implement
}

export function length(v) {
    // TODO implement
}

export function normalize(v) {
    // TODO implement
}

export function project(v, w) {
    // TODO implement
}

export function reflect(v, w) {
    // TODO implement
}

export function angle(v, w) {
    // TODO implement
}
