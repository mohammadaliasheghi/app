import PropTypes from "prop-types";
/* eslint-disable react/prop-types */
const Counter = ({inc, dec, res, count}) => {

    const changeCountColor = () => {
        if (count === 0)
            return 'yellow';
        else if (count > 0)
            return 'green'
        else if (count < 0)
            return 'red';
        else return 'aqua';
    }

    return (
        <div>
            <h1 style={{color: changeCountColor()}}>{count}</h1>

            <button onClick={inc}>+</button>
            <button onClick={dec} disabled={count === -5}
                    style={{backgroundColor: count === -5 ? 'aqua' : '#1a1a1a'}}>
                -
            </button>
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