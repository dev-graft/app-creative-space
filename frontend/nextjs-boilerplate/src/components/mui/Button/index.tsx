import {Components} from '@/types';
import {Button} from '@mui/material';


type ButtonProps = {
    size: typeof Components.SizeKind.small | typeof Components.SizeKind.medium | typeof Components.SizeKind.large,
    value: string
};

function MyButton({value, size}: ButtonProps) {
    return (
        <Button variant='contained' size={size}>
            {value}
        </Button>
    );
}



export default MyButton