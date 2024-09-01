import './App.css'
import Counter from "./Counter.jsx";
import {useState} from "react";

function App() {

    const [count, setCount] = useState(0);

    const increaseCount = () => {
        setCount(count + 1);
    }

    const decreaseCount = () => {
        setCount(count - 1)
    }

    const resetCount = () => {
        setCount(0);
    }

    return (
        <>
            <Counter inc={increaseCount} dec={decreaseCount} res={resetCount} count={count}/>
        </>
    )
}

export default App
