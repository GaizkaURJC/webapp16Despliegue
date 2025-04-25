export interface EventDTO{
    id: number;
    title: string;
    type: string;
    description: string;
    category: string;

}
export interface EventWhithImageDTO extends EventDTO{
    image: string;
}