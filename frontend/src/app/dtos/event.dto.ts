export interface EventDTO{
    id: number;
    title: string;
    type: string;
    description: string;
    category: string;

}
export interface EventWithImageDTO extends EventDTO{
    image: string;
}