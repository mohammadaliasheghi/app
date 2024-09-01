import PropTypes from "prop-types";
/* eslint-disable react/prop-types */
const Counter = ({inc, dec, res, count}) => {
    return (
        <div>
            <h1>{count}</h1>

            <button onClick={inc}>+</button>
            <button onClick={dec}>-</button>
            <br/>
            <button onClick={res}>o</button>
        </div>
    )
}

Counter.propTypes = {
    inc: PropTypes.func,
    des: PropTypes.func,
    res: PropTypes.func,
    count: PropTypes.number
}

export default Counter;