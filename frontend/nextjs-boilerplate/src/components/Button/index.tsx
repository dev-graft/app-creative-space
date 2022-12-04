import * as Styled from './style'
import {Components} from "@/types";

function Button({children, ...props}: Components.ButtonProps) {
    return (
        <Styled.Container {...props}>
            {children}
        </Styled.Container>
    )
}

const defaultProps: Components.ButtonProps = {
    children: null,
    size: Components.SizeKind.medium,
    styleType: 'solid'
};

Button.defaultProps = defaultProps;

export default Button