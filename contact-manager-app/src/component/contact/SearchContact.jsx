import {PURPLE} from "../../constant/Color.jsx";
import Messages from "../../constant/local/Messages.jsx";

const SearchContact = () => {
    return (
        <div className="input-group mx-2 w-75" dir="ltr">
      <span
          className="input-group-text"
          id="basic-addon1"
          style={{backgroundColor: PURPLE}}>
        <i className="fas fa-search"/>
      </span>
            <input
                dir="rtl"
                type="text"
                className="form-control"
                placeholder={Messages.fa.messages['search-contact']}
                aria-label="Search"
                aria-describedby="basic-addon1"
            />
        </div>
    );
};

export default SearchContact;
