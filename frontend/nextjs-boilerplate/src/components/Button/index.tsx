import {Components} from '@/types';
import {Button, ButtonProps} from '@mui/material';


type CustomButtonProps = ButtonProps&{
    size: typeof Components.SizeKind.small | typeof Components.SizeKind.medium | typeof Components.SizeKind.large,
    value: string
};

function MyButton({value, size}: CustomButtonProps) {
    return (
        <Button variant='contained' size={size}>
            {value}
        </Button>
    );
}

const defaultProps: Components.ButtonProps = {
    children: null,
    size: Components.SizeKind.medium,
    styleType: 'solid'
};

MyButton.defaultProps = defaultProps;

export default MyButton