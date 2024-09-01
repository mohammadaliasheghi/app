import SearchContact from "./contact/SearchContact";
import Messages from "../constant/local/Messages.jsx";

const Navbar = () => {
    return (
        <nav className="navbar navbar-dark navbar-expand-sm shadow-lg">
            <div className="container">
                <div className="row w-100">
                    <div className="col">
                        <i className="fas fa-id-badge"/>
                        {Messages.fa.messages['pwa-management']}{"  "}
                        <span style={{color: "purple"}}>{Messages.fa.messages['contact']}</span>
                    </div>
                    <div className="col">
                        <SearchContact/>
                    </div>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
